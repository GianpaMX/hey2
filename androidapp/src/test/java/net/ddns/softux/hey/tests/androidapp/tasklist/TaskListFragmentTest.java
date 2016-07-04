package net.ddns.softux.hey.tests.androidapp.tasklist;

import android.os.Build;
import android.support.annotation.NonNull;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.androidapp.tasklist.TaskListAdapter;
import net.ddns.softux.hey.androidapp.tasklist.TaskListFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 2/07/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP_MR1)
public class TaskListFragmentTest {

    private TestableTaskListFragment taskListFragment;

    @Before
    public void setUp() {
        taskListFragment = new TestableTaskListFragment();
        SupportFragmentTestUtil.startFragment(taskListFragment);
    }

    @Test
    public void onTaskListLoad() {
        taskListFragment.loadTaskList(new ArrayList<TaskViewModel>());
        verify(taskListFragment.mockTaskListAdapter).swapTaskViewModelList(any(List.class));
    }

    @Test
    public void addTask() {
        TaskViewModel expectedTaskViewModel = new TaskViewModel();
        taskListFragment.addTask(expectedTaskViewModel);
        verify(taskListFragment.mockTaskListAdapter).addTaskViewModel(expectedTaskViewModel);
    }

    public static class TestableTaskListFragment extends TaskListFragment {
        public TaskListAdapter mockTaskListAdapter = mock(TaskListAdapter.class);

        @NonNull
        @Override
        protected TaskListAdapter newTaskListAdapter(TaskListFragmentContainerListener taskListFragmentContainerListener) {
            return mockTaskListAdapter;
        }
    }
}
