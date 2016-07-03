package net.ddns.softux.hey.androidapp.tasklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.AndroidApp;
import net.ddns.softux.hey.androidapp.BaseActivity;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskActivity;
import net.ddns.softux.hey.androidapp.tasklist.di.TaskListActivityModule;
import net.ddns.softux.hey.androidapp.tasklist.di.TaskListComponent;
import net.ddns.softux.hey.todoapp.tasklist.TaskListUseCase;

import javax.inject.Inject;

/**
 * Created by juan on 2/07/16.
 */
public class TaskListActivity extends BaseActivity {

    @Inject
    public TaskListPresenter taskListPresenter;

    @Inject
    public TaskListUseCase taskListUseCase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_activity);

        getTaskListActivityModule().inject(this);

        setupToolbar();

        ImageButton newTaskButton = (ImageButton) findViewById(R.id.new_task_button);
        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskListActivity.this, AddEditTaskActivity.class);
                startActivity(intent);
            }
        });

        TaskListFragment taskListFragment = (TaskListFragment) getSupportFragmentManager().findFragmentById(R.id.task_list_fragment);
        if (taskListFragment == null) {
            taskListFragment = TaskListFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.task_list_fragment, taskListFragment).commit();
        }
        taskListPresenter.view = taskListFragment;
    }

    @Override
    protected void onStart() {
        super.onStart();
        taskListUseCase.start(taskListPresenter);
    }

    private TaskListComponent getTaskListActivityModule() {
        return ((AndroidApp) getApplication()).getAndroidAppComponent().add(new TaskListActivityModule(this));
    }
}
