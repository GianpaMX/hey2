package net.ddns.softux.hey.addedittask;

import net.ddns.softux.hey.androidapp.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by juan on 30/06/16.
 */

@Module
public class AddEditTaskActivityModule {
    private AddEditTaskActivity addEditTaskActivity;

    public AddEditTaskActivityModule(AddEditTaskActivity addEditTaskActivity) {
        this.addEditTaskActivity = addEditTaskActivity;
    }

    @Provides
    @ActivityScope
    public AddEditTaskActivity provideAddEditTaskActivity() {
        return addEditTaskActivity;
    }

    @Provides
    @ActivityScope
    public AddEditTaskPresenter provideAddEditTaskPresenter() {
        return new AddEditTaskPresenter();
    }
}
