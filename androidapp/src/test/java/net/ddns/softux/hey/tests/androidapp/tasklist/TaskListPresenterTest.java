package net.ddns.softux.hey.tests.androidapp.tasklist;

import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.androidapp.tasklist.TaskListPresenter;
import net.ddns.softux.hey.androidapp.tasklist.TaskListView;
import net.ddns.softux.hey.todoapp.data.Task;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;
import net.ddns.softux.hey.todoapp.tasklist.TaskListUseCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TaskListPresenterTest {

    private TaskListPresenter taskListPresenter;

    private TaskListView mockTaskListView;
    private TaskListUseCase taskListUseCase;
    private SaveTaskUseCase saveTaskUseCase;

    @Before
    public void setUp() {
        mockTaskListView = mock(TaskListView.class);
        taskListUseCase = mock(TaskListUseCase.class);
        saveTaskUseCase = mock(SaveTaskUseCase.class);

        taskListPresenter = new TaskListPresenter(taskListUseCase, saveTaskUseCase);
        taskListPresenter.view = mockTaskListView;
    }

    @Test
    public void onTaskListLoad() throws Exception {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());

        taskListPresenter.onTaskListChanged(tasks);

        verify(mockTaskListView).loadTaskList((List<TaskViewModel>) argThat(hasSize(1)));
    }
}