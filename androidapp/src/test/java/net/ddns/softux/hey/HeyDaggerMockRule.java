package net.ddns.softux.hey;

import net.ddns.softux.hey.androidapp.AndroidApp;
import net.ddns.softux.hey.androidapp.AndroidAppComponent;
import net.ddns.softux.hey.androidapp.AndroidAppModule;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Created by juan on 5/06/16.
 */

public class HeyDaggerMockRule extends DaggerMockRule<AndroidAppComponent> {
    public HeyDaggerMockRule(final AndroidApp androidApp) {
        super(AndroidAppComponent.class, new AndroidAppModule(androidApp));
        set(new ComponentSetter<AndroidAppComponent>() {
            @Override
            public void setComponent(AndroidAppComponent component) {
                androidApp.setAppComponent(component);
            }
        });
    }
}
