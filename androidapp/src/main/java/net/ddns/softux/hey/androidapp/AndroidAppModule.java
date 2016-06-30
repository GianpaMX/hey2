package net.ddns.softux.hey.androidapp;

import android.content.Context;

import net.ddns.softux.hey.todoapp.savetask.SaveTaskInteractor;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by juan on 30/06/16.
 */

@Module
public class AndroidAppModule {
    Context context;

    public AndroidAppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

//    @Provides
//    public SaveTaskUseCase provideTaskUseCase(SaveTaskGateway saveTaskGateway, OnSaveTaskListener onSaveTaskListener) {
//        return new SaveTaskInteractor(saveTaskGateway, onSaveTaskListener);
//    }
//
    @Provides
    public SaveTaskUseCase provideTaskUseCase() {
        return new SaveTaskInteractor(null, null);
    }
}
