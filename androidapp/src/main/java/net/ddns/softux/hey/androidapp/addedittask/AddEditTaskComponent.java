package net.ddns.softux.hey.androidapp.addedittask;

import net.ddns.softux.hey.androidapp.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by juan on 30/06/16.
 */

@ActivityScope
@Subcomponent(modules = {AddEditTaskActivityModule.class})
public interface AddEditTaskComponent {
    void inject(AddEditTaskActivity addEditTaskActivity);
}
