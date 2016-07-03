package net.ddns.softux.hey.androidapp.addedittask;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.AndroidApp;
import net.ddns.softux.hey.androidapp.BaseActivity;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;

import javax.inject.Inject;

public class AddEditTaskActivity extends BaseActivity implements AddEditTaskFragment.AddEditTaskFragmentContainerListener {

    public static final String TASK_VIEW_MODEL = "TASK_VIEW_MODEL";
    @Inject
    public SaveTaskUseCase saveTaskUseCase;

    @Inject
    public AddEditTaskPresenter addEditTaskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_task_activity);

        getAddEditTaskActivityModule().inject(this);

        setupToolbar();

        AddEditTaskFragment addEditTaskFragment = getTaskFragment();
        if (addEditTaskFragment == null) {
            addEditTaskFragment = newAddEditTaskFragmentInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.task_fragment, addEditTaskFragment).commit();
        }
        addEditTaskPresenter.setView(addEditTaskFragment);
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

    protected AddEditTaskComponent getAddEditTaskActivityModule() {
        return ((AndroidApp) getApplication()).getAndroidAppComponent().add(new AddEditTaskActivityModule(this));
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
                saveTaskUseCase.save(getTaskViewModel().toTask(), addEditTaskPresenter);
                return true;
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
        Toast.makeText(this, taskViewModel.key, Toast.LENGTH_LONG).show();
        finish();
    }
}
