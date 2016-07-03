package net.ddns.softux.hey.tests.androidapp;

import android.os.Build;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.androidapp.AndroidApp;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by juan on 2/07/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP_MR1)
public abstract class ActivityTest {
    @Rule
    public HeyDaggerMockRule mockRule = new HeyDaggerMockRule((AndroidApp) RuntimeEnvironment.application);
}
