package net.ddns.softux.hey.tests.androidapp.addedittask;

import android.content.Context;
import android.os.Build;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskFragment;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP_MR1)
public class AddEditTaskFragmentTest {
    @Test
    public void showSuccess() throws Exception {
        TaskViewModel expectedTaskViewModel = new TaskViewModel("key", "title", "description");

        AddEditTaskFragment.AddEditTaskFragmentContainerListener mockContainerListener = mock(AddEditTaskFragment.AddEditTaskFragmentContainerListener.class);

        TestableAddEditTaskFragment addEditTaskFragment = new TestableAddEditTaskFragment();
        addEditTaskFragment.testableContainerListener = mockContainerListener;

        SupportFragmentTestUtil.startFragment(addEditTaskFragment);

        addEditTaskFragment.setTaskViewModel(expectedTaskViewModel);
        addEditTaskFragment.showSuccess();

        verify(mockContainerListener).onTaskSavedSuccessfully(expectedTaskViewModel);
    }

    public static class TestableAddEditTaskFragment extends AddEditTaskFragment {
        public AddEditTaskFragmentContainerListener testableContainerListener;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            containerListener = testableContainerListener;
        }
    }
}