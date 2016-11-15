package net.ddns.softux.hey.androidapp;

import android.app.Application;

import net.ddns.softux.hey.androidapp.di.AndroidAppComponent;
import net.ddns.softux.hey.androidapp.di.AndroidAppModule;
import net.ddns.softux.hey.androidapp.di.DaggerAndroidAppComponent;

public class AndroidApp extends Application {
    private AndroidAppComponent androidAppComponent;

    public AndroidAppComponent getAndroidAppComponent() {
        if (androidAppComponent == null) {
            androidAppComponent = DaggerAndroidAppComponent.builder()
                    .androidAppModule(new AndroidAppModule(this))
                    .build();
        }
        return androidAppComponent;
    }

    public void setAppComponent(AndroidAppComponent heyComponent) {
        this.androidAppComponent = heyComponent;
    }
}
