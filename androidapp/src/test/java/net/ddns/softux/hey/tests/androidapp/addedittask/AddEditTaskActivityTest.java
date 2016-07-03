package net.ddns.softux.hey.tests.androidapp.addedittask;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskActivity;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskPresenter;
import net.ddns.softux.hey.tests.androidapp.ActivityTest;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;
import net.ddns.softux.hey.todoapp.savetask.Task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by juan on 30/06/16.
 */
public class AddEditTaskActivityTest extends ActivityTest {
    @Mock
    public SaveTaskUseCase saveTaskUseCase;

    @Mock
    public AddEditTaskPresenter addEditTaskPresenter;

    @Test
    public void saveTask() {
        MenuItem mockSaveMenuItem = mock(MenuItem.class);
        when(mockSaveMenuItem.getItemId()).thenReturn(R.id.save);

        AddEditTaskActivity addEditTaskActivity = Robolectric.setupActivity(AddEditTaskActivity.class);
        addEditTaskActivity.onOptionsItemSelected(mockSaveMenuItem);

        verify(saveTaskUseCase).save(any(Task.class), any(AddEditTaskPresenter.class));
    }

    @Test
    public void unknownOptionItemSelected() {
        AddEditTaskActivity addEditTaskActivity = Robolectric.setupActivity(AddEditTaskActivity.class);
        addEditTaskActivity.onOptionsItemSelected(new RoboMenuItem(0));
    }

    @Test
    public void recreateActivity() {
        Activity activity;
        ActivityController<AddEditTaskActivity> controller = Robolectric.buildActivity(AddEditTaskActivity.class);

        activity = controller.create().start().postCreate(null).resume().visible().get();
        assertTrue("Activity should be instanciated", activity instanceof AddEditTaskActivity);

        Bundle bundle = new Bundle();

        // Destroy the original activity
        controller
                .saveInstanceState(bundle)
                .pause()
                .stop()
                .destroy();

        // Bring up a new activity
        controller = Robolectric.buildActivity(AddEditTaskActivity.class)
                .create(bundle)
                .start()
                .restoreInstanceState(bundle)
                .resume()
                .visible();

        activity = controller.get();
        assertTrue("Activity should be instanciated", activity instanceof AddEditTaskActivity);
    }
}
