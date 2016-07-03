package net.ddns.softux.hey.androidapp.tasklist.di;

import net.ddns.softux.hey.androidapp.di.ActivityScope;
import net.ddns.softux.hey.androidapp.tasklist.TaskListActivity;

import dagger.Subcomponent;

/**
 * Created by juan on 3/07/16.
 */
@ActivityScope
@Subcomponent(modules = {TaskListActivityModule.class})
public interface TaskListComponent {
    void inject(TaskListActivity taskListActivity);
}
