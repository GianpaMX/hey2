package net.ddns.softux.hey.androidapp.addedittask;

import net.ddns.softux.hey.androidapp.di.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {AddEditTaskActivityModule.class})
public interface AddEditTaskComponent {
    void inject(AddEditTaskActivity addEditTaskActivity);
}
