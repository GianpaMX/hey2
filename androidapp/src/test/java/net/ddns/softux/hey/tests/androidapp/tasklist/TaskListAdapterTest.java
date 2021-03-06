package net.ddns.softux.hey.tests.androidapp.tasklist;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.androidapp.tasklist.TaskListAdapter;
import net.ddns.softux.hey.androidapp.tasklist.TaskListFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP_MR1)
public class TaskListAdapterTest {

    private TaskListAdapter taskListAdapter;

    @Mock
    private RecyclerView.AdapterDataObserver mockObserver;
    @Mock
    private TaskListFragment.TaskListFragmentContainerListener mockFragmentContainerListener;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        taskListAdapter = new TaskListAdapter(mockFragmentContainerListener);
        taskListAdapter.registerAdapterDataObserver(mockObserver);
    }

    @After
    public void tearDown() {
        taskListAdapter.unregisterAdapterDataObserver(mockObserver);
    }

    @Test
    public void swapTaskViewModelList() {
        ArrayList<TaskViewModel> emptyItemList = new ArrayList<>();

        ArrayList<TaskViewModel> oneItemList = new ArrayList<>();
        oneItemList.add(new TaskViewModel());

        taskListAdapter.swapTaskViewModelList(oneItemList);
        taskListAdapter.swapTaskViewModelList(emptyItemList);

        verify(mockObserver, times(2)).onChanged();
    }

    @Test
    public void addTaskViewModel() {
        taskListAdapter.addTaskViewModel(new TaskViewModel());
        verify(mockObserver).onItemRangeInserted(0, 1);
    }

    @Test
    public void onCreateViewHolder() {
        ArrayList<TaskViewModel> taskViewModelList = new ArrayList<>();
        taskViewModelList.add(new TaskViewModel());

        View mockView = mock(View.class);
        when(mockView.findViewById(R.id.title)).thenReturn(mock(TextView.class));
        when(mockView.findViewById(R.id.checkbox)).thenReturn(mock(CheckBox.class));

        TaskListAdapter.ViewHolder viewHolder = new TaskListAdapter.ViewHolder(mockView, mock(TaskListAdapter.ViewHolder.TaskListItemListener.class));

        taskListAdapter.swapTaskViewModelList(taskViewModelList);
        taskListAdapter.onBindViewHolder(viewHolder, 0);

        verify(mockView).setOnLongClickListener(any(View.OnLongClickListener.class));
    }

    @Test
    public void onLongClick() {
        TaskViewModel expectedTaskViewModel = mock(TaskViewModel.class);

        TaskListAdapter.ViewHolder viewHolder = taskListAdapter.onCreateViewHolder(new FrameLayout(RuntimeEnvironment.application), 0);
        viewHolder.itemView.setTag(expectedTaskViewModel);

        when(mockFragmentContainerListener.onLongClickTask(any(TaskViewModel.class))).thenReturn(true);
        viewHolder.itemView.performLongClick();

        verify(mockFragmentContainerListener).onLongClickTask(expectedTaskViewModel);
    }

    @Test
    public void onCheckedListener() {
        CompoundButton mockCompoundButton = mock(CompoundButton.class);
        when(mockCompoundButton.getParent()).thenReturn((ViewParent) new FrameLayout(RuntimeEnvironment.application));

        taskListAdapter.getOnCheckedChangeListener().onCheckedChanged(mockCompoundButton, true);

        verify(mockFragmentContainerListener).onCheckedTask(any(TaskViewModel.class));
    }

    @Test
    public void onUnCheckedListener() {
        CompoundButton mockCompoundButton = mock(CompoundButton.class);
        when(mockCompoundButton.getParent()).thenReturn((ViewParent) new FrameLayout(RuntimeEnvironment.application));

        taskListAdapter.getOnCheckedChangeListener().onCheckedChanged(mockCompoundButton, false);

        verify(mockFragmentContainerListener).onUncheckedTask(any(TaskViewModel.class));
    }
}
