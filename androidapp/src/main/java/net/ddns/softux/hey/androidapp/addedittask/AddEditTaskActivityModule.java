package net.ddns.softux.hey.androidapp.addedittask;

import net.ddns.softux.hey.androidapp.di.ActivityScope;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;

import dagger.Module;
import dagger.Provides;

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
    public AddEditTaskPresenter provideAddEditTaskPresenter(SaveTaskUseCase saveTaskUseCase) {
        return new AddEditTaskPresenter(saveTaskUseCase);
    }
}
