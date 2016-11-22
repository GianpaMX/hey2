package net.ddns.softux.hey.tests.androidapp.task;

import android.os.Build;
import android.os.Parcel;

import net.ddns.softux.hey.BuildConfig;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.todoapp.data.Task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.KITKAT)
public class TaskViewModelTest {
    @Test
    public void equals() {
        TaskViewModel taskViewModel = new TaskViewModel();
        TaskViewModel emptyTaskViewModel = new TaskViewModel();

        assertEquals(taskViewModel, taskViewModel);
        assertEquals(taskViewModel, emptyTaskViewModel);
        assertNotEquals(taskViewModel, null);
    }

    @Test
    public void notEquals() {
        TaskViewModel taskViewModel = new TaskViewModel("key", "title", "description");
        TaskViewModel emptyTaskViewModel = new TaskViewModel();

        assertNotEquals(taskViewModel, emptyTaskViewModel);
    }

    @Test
    public void taskConstructor() {
        Task task = new Task("key", "title", "description");
        TaskViewModel taskViewModel = new TaskViewModel(task);

        assertEquals(task.key, taskViewModel.key);
        assertEquals(task.title, taskViewModel.title);
        assertEquals(task.description, taskViewModel.description);
    }

    @Test
    public void writeToandReadFromParcel() {
        TaskViewModel taskViewModel = new TaskViewModel();

        // Obtain a Parcel object and write the parcelable object to it:
        Parcel parcel = Parcel.obtain();
        taskViewModel.writeToParcel(parcel, 0);

        // After you're done with writing, you need to reset the parcel for reading:
        parcel.setDataPosition(0);

        // Reconstruct object from parcel and asserts:
        TaskViewModel createdFromParcel = TaskViewModel.CREATOR.createFromParcel(parcel);

        assertEquals(taskViewModel, createdFromParcel);
    }
}