package net.ddns.softux.hey.tests.androidapp.tasklist;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.AndroidApp;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskActivity;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.androidapp.tasklist.TaskListActivity;
import net.ddns.softux.hey.tests.androidapp.HeyDaggerMockRule;
import net.ddns.softux.hey.todoapp.savetask.OnSaveTaskListener;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;
import net.ddns.softux.hey.todoapp.task.Task;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by juan on 2/07/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP_MR1)
public class TaskListActivityTest {
    @Rule
    public HeyDaggerMockRule mockRule = new HeyDaggerMockRule((AndroidApp) RuntimeEnvironment.application);

    @Mock
    public SaveTaskUseCase saveTaskUseCase;

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

    @Test
    public void onCheckedTask() {
        taskListActivity.onCheckedTask(mock(TaskViewModel.class));

        verify(saveTaskUseCase).check(any(Task.class), any(OnSaveTaskListener.class));
    }

    @Test
    public void onUncheckedTask() {
        taskListActivity.onUncheckedTask(mock(TaskViewModel.class));

        verify(saveTaskUseCase).uncheck(any(Task.class), any(OnSaveTaskListener.class));
    }
}
