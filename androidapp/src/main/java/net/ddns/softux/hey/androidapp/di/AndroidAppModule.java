package net.ddns.softux.hey.androidapp.di;

import android.content.Context;

import net.ddns.softux.hey.todoapp.data.Task;
import net.ddns.softux.hey.todoapp.data.TaskMemoryRepository;
import net.ddns.softux.hey.todoapp.data.TaskRepository;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskInteractor;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;
import net.ddns.softux.hey.todoapp.tasklist.TaskListInteractor;
import net.ddns.softux.hey.todoapp.tasklist.TaskListUseCase;

import java.util.Hashtable;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
    public TaskRepository provideTaskRepository() {
        Map<String, Task> tasks = new Hashtable<>();
        return new TaskMemoryRepository(tasks, 0);
    }

    @Provides
    @Singleton
    public SaveTaskUseCase provideSaveTaskUseCase(TaskRepository taskRepository) {
        return new SaveTaskInteractor(taskRepository);
    }

    @Provides
    @Singleton
    public TaskListUseCase provideTaskListUseCase(TaskRepository taskRepository) {
        return new TaskListInteractor(taskRepository);
    }
}
