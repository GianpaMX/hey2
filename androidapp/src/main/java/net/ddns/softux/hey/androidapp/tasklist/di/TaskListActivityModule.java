package net.ddns.softux.hey.androidapp.tasklist.di;

import net.ddns.softux.hey.androidapp.di.ActivityScope;
import net.ddns.softux.hey.androidapp.tasklist.TaskListActivity;
import net.ddns.softux.hey.androidapp.tasklist.TaskListPresenter;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;
import net.ddns.softux.hey.todoapp.tasklist.TaskListUseCase;

import dagger.Module;
import dagger.Provides;

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
    public TaskListPresenter provideTaskListPresenter(TaskListUseCase taskListUseCase, SaveTaskUseCase saveTaskUseCase) {
        return new TaskListPresenter(taskListUseCase, saveTaskUseCase);
    }

}
