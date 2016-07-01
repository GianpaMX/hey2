package net.ddns.softux.hey.tests.androidapp.addedittask.addedittask;

import android.os.Build;
import android.view.MenuItem;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.tests.androidapp.HeyDaggerMockRule;
import net.ddns.softux.hey.R;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskActivity;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskPresenter;
import net.ddns.softux.hey.androidapp.AndroidApp;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;
import net.ddns.softux.hey.todoapp.savetask.Task;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by juan on 30/06/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.KITKAT)
public class AddEditTaskActivityTest {
    @Rule
    public HeyDaggerMockRule mockRule = new HeyDaggerMockRule((AndroidApp) RuntimeEnvironment.application);

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
}
