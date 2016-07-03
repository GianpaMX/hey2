package net.ddns.softux.hey.tests.todoapp.tasklist;

import net.ddns.softux.hey.todoapp.task.Task;
import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.tasklist.OnTaskListLoadListener;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;
import net.ddns.softux.hey.todoapp.tasklist.TaskListInteractor;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 3/07/16.
 */
public class TaskListInteractorTest {
    @Test
    public void start() throws Exception {
        TaskListGateway mockTaskListGateway = mock(TaskListGateway.class);

        TaskListInteractor taskListInteractor = new TaskListInteractor(mockTaskListGateway);
        taskListInteractor.start(mock(OnTaskListLoadListener.class));

        verify(mockTaskListGateway).loadTaskList(any(TaskListGateway.OnTaskListGatewayListener.class));
    }

    @Test
    public void onTaskListLoad() throws Exception {
        ArrayList<TaskEntitity> expectedTaskEntitities = new ArrayList<>();
        expectedTaskEntitities.add(new TaskEntitity());

        TaskListGateway mockTaskListGateway = mock(TaskListGateway.class);
        OnTaskListLoadListener mockOnTaskListLoadListener = mock(OnTaskListLoadListener.class);

        ArgumentCaptor<TaskListGateway.OnTaskListGatewayListener> argumentCaptor = ArgumentCaptor.forClass(TaskListGateway.OnTaskListGatewayListener.class);

        TaskListInteractor taskListInteractor = new TaskListInteractor(mockTaskListGateway);
        taskListInteractor.start(mockOnTaskListLoadListener);

        verify(mockTaskListGateway).loadTaskList(argumentCaptor.capture());

        argumentCaptor.getValue().onTaskListLoad(expectedTaskEntitities);

        verify(mockOnTaskListLoadListener).onTaskListLoad((List<Task>) argThat(hasSize(1)));
    }

}