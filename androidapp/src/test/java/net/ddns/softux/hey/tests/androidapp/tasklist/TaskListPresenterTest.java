package net.ddns.softux.hey.tests.androidapp.tasklist;

import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.androidapp.tasklist.TaskListPresenter;
import net.ddns.softux.hey.androidapp.tasklist.TaskListView;
import net.ddns.softux.hey.todoapp.task.Task;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 3/07/16.
 */
public class TaskListPresenterTest {
    @Test
    public void onTaskListLoad() throws Exception {
        TaskListView mockTaskListView = mock(TaskListView.class);

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());

        TaskListPresenter taskListPresenter = new TaskListPresenter();
        taskListPresenter.view = mockTaskListView;

        taskListPresenter.onTaskListLoad(tasks);

        verify(mockTaskListView).loadTaskList((List<TaskViewModel>) argThat(hasSize(1)));
    }

}