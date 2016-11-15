package net.ddns.softux.hey.androidapp.di;

import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskActivityModule;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskComponent;
import net.ddns.softux.hey.androidapp.tasklist.di.TaskListActivityModule;
import net.ddns.softux.hey.androidapp.tasklist.di.TaskListComponent;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AndroidAppModule.class})
@Singleton
public interface AndroidAppComponent {
    AddEditTaskComponent add(AddEditTaskActivityModule addEditTaskActivityModule);

    TaskListComponent add(TaskListActivityModule taskListActivityModule);
}
