package net.ddns.softux.hey.tests.androidapp.tasklist;

import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.androidapp.tasklist.TaskListPresenter;
import net.ddns.softux.hey.androidapp.tasklist.TaskListView;
import net.ddns.softux.hey.todoapp.task.Task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 3/07/16.
 */
public class TaskListPresenterTest {

    private TaskListPresenter taskListPresenter;

    private TaskListView mockTaskListView;

    @Before
    public void setUp() {
        mockTaskListView = mock(TaskListView.class);

        taskListPresenter = new TaskListPresenter();
        taskListPresenter.view = mockTaskListView;
    }

    @Test
    public void onTaskListLoad() throws Exception {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());

        taskListPresenter.onTaskListLoad(tasks);

        verify(mockTaskListView).loadTaskList((List<TaskViewModel>) argThat(hasSize(1)));
    }

    @Test
    public void onTaskAdded() {
        Task expectedTask = new Task("key", "title", "value");

        ArgumentCaptor<TaskViewModel> argumentCaptor = ArgumentCaptor.forClass(TaskViewModel.class);

        taskListPresenter.onTaskAdded(expectedTask);
        verify(mockTaskListView).addTask(argumentCaptor.capture());

        TaskViewModel addedTask = argumentCaptor.getValue();

        assertThat(expectedTask.key).isEqualTo(addedTask.key);
        assertThat(expectedTask.title).isEqualTo(addedTask.title);
        assertThat(expectedTask.description).isEqualTo(addedTask.description);
    }
}