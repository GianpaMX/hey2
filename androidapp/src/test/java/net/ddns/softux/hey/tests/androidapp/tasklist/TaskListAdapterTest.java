package net.ddns.softux.hey.tests.androidapp.tasklist;

import android.os.Build;
import android.support.v7.widget.RecyclerView;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.androidapp.tasklist.TaskListAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 2/07/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP_MR1)
public class TaskListAdapterTest {

    private RecyclerView.AdapterDataObserver mockObserver;
    private TaskListAdapter taskListAdapter;

    @Before
    public void setUp() {
        mockObserver = mock(RecyclerView.AdapterDataObserver.class);
        taskListAdapter = new TaskListAdapter();
        taskListAdapter.registerAdapterDataObserver(mockObserver);
    }

    @Test
    public void swapTaskViewModelList() {
        taskListAdapter.swapTaskViewModelList(new ArrayList<TaskViewModel>());
        verify(mockObserver).onChanged();
    }

    @Test
    public void addTaskViewModel() {
        taskListAdapter.addTaskViewModel(new TaskViewModel());
        verify(mockObserver).onItemRangeInserted(0, 1);
    }
}
