package net.ddns.softux.hey.androidapp.tasklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageButton;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.AndroidApp;
import net.ddns.softux.hey.androidapp.BaseActivity;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskActivity;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.androidapp.tasklist.di.TaskListActivityModule;
import net.ddns.softux.hey.androidapp.tasklist.di.TaskListComponent;

import javax.inject.Inject;

public class TaskListActivity extends BaseActivity implements TaskListFragment.TaskListFragmentContainerListener {

    private static final int EDIT_REQUEST_CODE = 1;

    @Inject
    public TaskListPresenter taskListPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_activity);

        inject(this);

        setupToolbar();

        ImageButton newTaskButton = (ImageButton) findViewById(R.id.new_task_button);
        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskListActivity.this, AddEditTaskActivity.class);
                startActivity(intent);
            }
        });

        taskListPresenter.view = getTaskListFragment();
    }

    @NonNull
    private TaskListFragment getTaskListFragment() {
        TaskListFragment taskListFragment = (TaskListFragment) getSupportFragmentManager().findFragmentById(R.id.task_list_fragment);

        if (taskListFragment == null) {
            taskListFragment = TaskListFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.task_list_fragment, taskListFragment).commit();
        }

        return taskListFragment;
    }

    private void inject(TaskListActivity activity) {
        getTaskListActivityModule().inject(activity);
    }

    public TaskListComponent getTaskListActivityModule() {
        return ((AndroidApp) getApplication()).getAndroidAppComponent().add(new TaskListActivityModule(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        taskListPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        taskListPresenter.stop();
    }

    @Override
    public boolean onLongClickTask(TaskViewModel taskViewModel) {
        Intent intent = new Intent(this, AddEditTaskActivity.class);
        intent.putExtra(AddEditTaskActivity.TASK_VIEW_MODEL, taskViewModel);
        startActivityForResult(intent, EDIT_REQUEST_CODE);

        return true;
    }

    @Override
    public void onCheckedTask(TaskViewModel taskViewModel) {
        taskListPresenter.check(taskViewModel);
    }

    @Override
    public void onUncheckedTask(TaskViewModel taskViewModel) {
        taskListPresenter.uncheck(taskViewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == EDIT_REQUEST_CODE && resultCode == AddEditTaskActivity.RESULT_REMOVED) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.task_list_activity), R.string.task_list_activity_task_removed_message, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.task_list_activity_task_removed_undo, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    taskListPresenter.restore((TaskViewModel) data.getParcelableExtra(AddEditTaskActivity.TASK_VIEW_MODEL));
                }
            });
            snackbar.show();

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
