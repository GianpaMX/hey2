package net.ddns.softux.hey.androidapp.addedittask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.AndroidApp;
import net.ddns.softux.hey.androidapp.BaseActivity;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;

import javax.inject.Inject;

public class AddEditTaskActivity extends BaseActivity implements AddEditTaskFragment.AddEditTaskFragmentContainerListener {

    public static final String TASK_VIEW_MODEL = "TASK_VIEW_MODEL";
    public static final int RESULT_REMOVED = 1;

    @Inject
    public AddEditTaskPresenter addEditTaskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_task_activity);

        inject(this);

        setupToolbar();

        AddEditTaskFragment addEditTaskFragment = getTaskFragment();
        if (addEditTaskFragment == null) {
            addEditTaskFragment = newAddEditTaskFragmentInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.task_fragment, addEditTaskFragment).commit();
        }
        addEditTaskPresenter.setView(addEditTaskFragment);

        // Default result
        setResult(RESULT_CANCELED);
    }

    private void inject(AddEditTaskActivity activity) {
        ((AndroidApp) getApplication()).getAndroidAppComponent().add(new AddEditTaskActivityModule(activity)).inject(activity);
    }

    private AddEditTaskFragment newAddEditTaskFragmentInstance() {
        AddEditTaskFragment addEditTaskFragment;
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(TASK_VIEW_MODEL)) {
            addEditTaskFragment = AddEditTaskFragment.newInstance((TaskViewModel) getIntent().getExtras().getParcelable(TASK_VIEW_MODEL));
        } else {
            addEditTaskFragment = AddEditTaskFragment.newInstance();
        }
        return addEditTaskFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_task_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                addEditTaskPresenter.save(getTaskViewModel());
                return true;
            case R.id.remove:
                addEditTaskPresenter.remove(getTaskViewModel());
        }

        return super.onOptionsItemSelected(item);
    }

    public TaskViewModel getTaskViewModel() {
        return getTaskFragment().getTaskViewModel();
    }

    public AddEditTaskFragment getTaskFragment() {
        return (AddEditTaskFragment) getSupportFragmentManager().findFragmentById(R.id.task_fragment);
    }

    @Override
    public void onTaskSavedSuccessfully(TaskViewModel taskViewModel) {
        Intent data = getDataIntentFromTaskViewModel(taskViewModel);
        setResult(RESULT_OK, data);

        finish();
    }

    @Override
    public void onTaskRemoved(TaskViewModel taskViewModel) {
        Intent data = getDataIntentFromTaskViewModel(taskViewModel);
        setResult(RESULT_REMOVED, data);

        finish();
    }

    @NonNull
    private Intent getDataIntentFromTaskViewModel(TaskViewModel taskViewModel) {
        Intent data = new Intent();
        data.putExtra(TASK_VIEW_MODEL, taskViewModel);
        return data;
    }
}
