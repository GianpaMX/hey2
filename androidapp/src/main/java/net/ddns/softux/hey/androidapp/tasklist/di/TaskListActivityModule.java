package net.ddns.softux.hey.androidapp.tasklist.di;

import net.ddns.softux.hey.androidapp.di.ActivityScope;
import net.ddns.softux.hey.androidapp.tasklist.TaskListActivity;
import net.ddns.softux.hey.androidapp.tasklist.TaskListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by juan on 3/07/16.
 */

@Module
public class TaskListActivityModule {
    private TaskListActivity taskListActivity;

    public TaskListActivityModule(TaskListActivity taskListActivity) {
        this.taskListActivity = taskListActivity;
    }

    @Provides
    @ActivityScope
    public TaskListActivity provideAddEditTaskActivity() {
        return taskListActivity;
    }

    @Provides
    @ActivityScope
    public TaskListPresenter provideTaskListPresenter() {
        return new TaskListPresenter();
    }

}
