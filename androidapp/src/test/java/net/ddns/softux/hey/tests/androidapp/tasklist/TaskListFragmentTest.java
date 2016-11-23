package net.ddns.softux.hey.tests.androidapp.tasklist;

import android.os.Build;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.androidapp.AndroidApp;
import net.ddns.softux.hey.androidapp.di.AndroidAppComponent;
import net.ddns.softux.hey.androidapp.di.AndroidAppModule;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.androidapp.tasklist.TaskListActivity;
import net.ddns.softux.hey.androidapp.tasklist.TaskListAdapter;
import net.ddns.softux.hey.androidapp.tasklist.TaskListFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import java.util.ArrayList;
import java.util.List;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP_MR1)
public class TaskListFragmentTest {

    @Rule
    public final DaggerMockRule<AndroidAppComponent> rule = new DaggerMockRule<>(AndroidAppComponent.class, new AndroidAppModule(RuntimeEnvironment.application))
            .set(new DaggerMockRule.ComponentSetter<AndroidAppComponent>() {
                @Override
                public void setComponent(AndroidAppComponent component) {
                    ((AndroidApp) RuntimeEnvironment.application).setAppComponent(component);
                }
            });

    @Mock
    TaskListFragment.TaskListFragmentContainerListener taskListFragmentContainerListener;
    
    @Mock
    private TaskListAdapter taskListAdapter;

    private TaskListFragment taskListFragment;

    @Before
    public void setUp() {
        taskListFragment = new TaskListFragment();
        SupportFragmentTestUtil.startFragment(taskListFragment, TaskListActivity.class);
    }

    @Test
    public void onTaskListLoad() {
        taskListFragment.loadTaskList(new ArrayList<TaskViewModel>());

        verify(taskListAdapter).swapTaskViewModelList(any(List.class));
    }
}
