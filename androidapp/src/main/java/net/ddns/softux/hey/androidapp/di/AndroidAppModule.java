package net.ddns.softux.hey.androidapp.di;

import android.content.Context;

import net.ddns.softux.hey.todoapp.savetask.SaveTaskGateway;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskInteractor;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;
import net.ddns.softux.hey.todoapp.savetask.data.SaveTaskInMemory;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;
import net.ddns.softux.hey.todoapp.tasklist.TaskListInteractor;
import net.ddns.softux.hey.todoapp.tasklist.TaskListUseCase;
import net.ddns.softux.hey.todoapp.tasklist.data.TaskListRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by juan on 30/06/16.
 */

@Module
public class AndroidAppModule {
    protected Context context;

    public AndroidAppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    public SaveTaskGateway provideSaveTaskGateway() {
        return new SaveTaskInMemory();
    }

    @Singleton
    @Provides
    public TaskListGateway provideTaskListGateway(SaveTaskGateway saveTaskGateway) {
        return new TaskListRepository(saveTaskGateway);
    }

    @Provides
    @Singleton
    public SaveTaskUseCase provideTaskUseCase(SaveTaskGateway saveTaskGateway) {
        return new SaveTaskInteractor(saveTaskGateway);
    }

    @Provides
    @Singleton
    public TaskListUseCase provideTaskListUseCase(TaskListGateway taskListGateway) {
        return new TaskListInteractor(taskListGateway);
    }
}
