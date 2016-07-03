package net.ddns.softux.hey.tests.androidapp.tasklist;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskActivity;
import net.ddns.softux.hey.androidapp.tasklist.TaskListActivity;
import net.ddns.softux.hey.tests.androidapp.ActivityTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by juan on 2/07/16.
 */

public class TaskListActivityTest extends ActivityTest {
    @Test
    public void clickNewTask() {
        TaskListActivity taskListActivity = Robolectric.setupActivity(TaskListActivity.class);
        FloatingActionButton newTaskButton = (FloatingActionButton) taskListActivity.findViewById(R.id.new_task_button);

        newTaskButton.performClick();

        Intent expectedIntent = new Intent(taskListActivity, AddEditTaskActivity.class);
        assertThat(shadowOf(taskListActivity).getNextStartedActivity().getComponent()).isEqualTo(expectedIntent.getComponent());
    }
}
