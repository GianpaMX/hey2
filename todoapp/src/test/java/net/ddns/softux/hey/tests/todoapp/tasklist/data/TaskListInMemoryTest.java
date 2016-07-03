package net.ddns.softux.hey.tests.todoapp.tasklist.data;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;
import net.ddns.softux.hey.todoapp.tasklist.data.TaskListInMemory;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 3/07/16.
 */
public class TaskListInMemoryTest {
    @Test
    public void loadEmptyTaskList() throws Exception {
        TaskListGateway.OnTaskListGatewayListener mockOnTaskListGatewayListener = mock(TaskListGateway.OnTaskListGatewayListener.class);

        TaskListInMemory taskListInMemory = new TaskListInMemory();
        taskListInMemory.loadTaskList(mockOnTaskListGatewayListener);

        verify(mockOnTaskListGatewayListener).onTaskListLoad((Collection<TaskEntitity>) argThat(hasSize(0)));
    }

    @Test
    public void loadOneElementTaskList() throws Exception {
        String expectedKey = "1";
        Map<String, TaskEntitity> expectedTasks = new HashMap<>();
        expectedTasks.put(expectedKey, new TaskEntitity(expectedKey, "title", "description"));

        TaskListGateway.OnTaskListGatewayListener mockOnTaskListGatewayListener = mock(TaskListGateway.OnTaskListGatewayListener.class);

        TaskListInMemory taskListInMemory = new TaskListInMemory(expectedTasks);
        taskListInMemory.loadTaskList(mockOnTaskListGatewayListener);

        verify(mockOnTaskListGatewayListener).onTaskListLoad((Collection<TaskEntitity>) argThat(hasSize(1)));
    }
}
