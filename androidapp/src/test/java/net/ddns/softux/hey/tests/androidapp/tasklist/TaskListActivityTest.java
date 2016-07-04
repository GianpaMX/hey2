package net.ddns.softux.hey.tests.androidapp.tasklist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;

import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskActivity;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.androidapp.tasklist.TaskListActivity;
import net.ddns.softux.hey.tests.androidapp.ActivityTest;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by juan on 2/07/16.
 */

public class TaskListActivityTest extends ActivityTest {

    private TaskListActivity taskListActivity;

    @Before
    public void setUp() {
        taskListActivity = Robolectric.setupActivity(TaskListActivity.class);
    }

    @Test
    public void clickNewTask() {
        FloatingActionButton newTaskButton = (FloatingActionButton) taskListActivity.findViewById(R.id.new_task_button);

        newTaskButton.performClick();

        Intent expectedIntent = new Intent(taskListActivity, AddEditTaskActivity.class);
        assertThat(shadowOf(taskListActivity).getNextStartedActivity().getComponent()).isEqualTo(expectedIntent.getComponent());
    }

    @Test
    public void onLongClickTask() {
        TaskViewModel expectedTaskViewModel = new TaskViewModel();
        taskListActivity.onLongClickTask(expectedTaskViewModel);

        Intent expectedIntent = new Intent(taskListActivity, AddEditTaskActivity.class);
        expectedIntent.putExtra(AddEditTaskActivity.TASK_VIEW_MODEL, expectedTaskViewModel);

        Intent nextStartedActivity = shadowOf(taskListActivity).getNextStartedActivity();
        assertThat(nextStartedActivity.getComponent()).isEqualTo(expectedIntent.getComponent());
        assertThat(nextStartedActivity.getParcelableExtra(AddEditTaskActivity.TASK_VIEW_MODEL)).isEqualTo(expectedTaskViewModel);
    }
}
