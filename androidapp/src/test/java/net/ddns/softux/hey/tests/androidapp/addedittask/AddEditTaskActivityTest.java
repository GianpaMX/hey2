package net.ddns.softux.hey.tests.androidapp.addedittask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskActivity;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskPresenter;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.tests.androidapp.ActivityTest;

import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.util.ActivityController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddEditTaskActivityTest extends ActivityTest {
    @Mock
    public AddEditTaskPresenter addEditTaskPresenter;

    @Test
    public void saveTask() {
        MenuItem mockSaveMenuItem = mock(MenuItem.class);
        when(mockSaveMenuItem.getItemId()).thenReturn(R.id.save);

        AddEditTaskActivity addEditTaskActivity = Robolectric.setupActivity(AddEditTaskActivity.class);
        addEditTaskActivity.onOptionsItemSelected(mockSaveMenuItem);

        verify(addEditTaskPresenter).save(any(TaskViewModel.class));
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

    @Test
    public void onTaskSavedSuccessfully() {
        AddEditTaskActivity addEditTaskActivity = Robolectric.setupActivity(AddEditTaskActivity.class);
        addEditTaskActivity.onTaskSavedSuccessfully(new TaskViewModel("key", "title", "description"));
        assertThat(addEditTaskActivity.isFinishing());
    }

    @Test
    public void onCreateEdit() {
        TaskViewModel expectedTaskViewModel = new TaskViewModel();

        Intent intent = new Intent(RuntimeEnvironment.application, AddEditTaskActivity.class);
        intent.putExtra(AddEditTaskActivity.TASK_VIEW_MODEL, expectedTaskViewModel);

        AddEditTaskActivity addEditTaskActivity = Robolectric.buildActivity(AddEditTaskActivity.class).withIntent(intent).create().get();
        assertThat(addEditTaskActivity.getTaskViewModel()).isEqualTo(expectedTaskViewModel);
    }
}
