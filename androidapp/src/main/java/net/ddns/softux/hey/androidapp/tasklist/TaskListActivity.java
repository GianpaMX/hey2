package net.ddns.softux.hey.androidapp.tasklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.AndroidApp;
import net.ddns.softux.hey.androidapp.BaseActivity;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskActivity;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.androidapp.tasklist.di.TaskListActivityModule;

import javax.inject.Inject;

public class TaskListActivity extends BaseActivity implements TaskListFragment.TaskListFragmentContainerListener {

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
        (((AndroidApp) getApplication()).getAndroidAppComponent().add(new TaskListActivityModule(activity))).inject(activity);
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
        startActivity(intent);

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
}
