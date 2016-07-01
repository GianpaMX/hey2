package net.ddns.softux.hey.androidapp;

import net.ddns.softux.hey.addedittask.AddEditTaskActivityModule;
import net.ddns.softux.hey.addedittask.AddEditTaskComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by juan on 30/06/16.
 */

@Component(modules = {AndroidAppModule.class})
@Singleton
public interface AndroidAppComponent {
    AddEditTaskComponent add(AddEditTaskActivityModule addEditTaskActivityModule);
}
