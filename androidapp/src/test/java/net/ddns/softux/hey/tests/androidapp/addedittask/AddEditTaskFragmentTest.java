package net.ddns.softux.hey.tests.androidapp.addedittask;

import android.os.Build;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskFragment;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static junit.framework.Assert.assertEquals;

/**
 * Created by juan on 2/07/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP_MR1)
public class AddEditTaskFragmentTest {
    @Test
    public void showSuccess() throws Exception {
        AddEditTaskFragment addEditTaskFragment = new AddEditTaskFragment();
        SupportFragmentTestUtil.startFragment(addEditTaskFragment);

        String expectedKey = "key";
        addEditTaskFragment.setTaskViewModel(new TaskViewModel(expectedKey, "title", "description"));
        addEditTaskFragment.showSuccess();

        assertEquals(expectedKey, ShadowToast.getTextOfLatestToast());
    }
}