package net.ddns.softux.hey.androidapp.tasklist.di;

import net.ddns.softux.hey.androidapp.di.ActivityScope;
import net.ddns.softux.hey.androidapp.tasklist.TaskListActivity;
import net.ddns.softux.hey.androidapp.tasklist.TaskListFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {TaskListActivityModule.class})
public interface TaskListComponent {
    void inject(TaskListActivity taskListActivity);

    void inject(TaskListFragment taskListFragment);
}
