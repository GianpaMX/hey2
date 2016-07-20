package net.ddns.softux.hey.tests.todoapp.tasklist.data;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;
import net.ddns.softux.hey.todoapp.tasklist.data.TaskListInMemory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 3/07/16.
 */
public class TaskListInMemoryTest {

    @Mock
    public TaskListGateway.OnTaskListGatewayListener mockOnTaskListGatewayListener;

    public TaskListInMemory taskListInMemory;
    public Map<String, TaskEntitity> expectedTasks;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        expectedTasks = new HashMap<>();
        taskListInMemory = new TaskListInMemory(expectedTasks);
        taskListInMemory.setOnTaskListGatewayListener(mockOnTaskListGatewayListener);
    }

    @Test
    public void loadEmptyTaskList() throws Exception {
        taskListInMemory.loadTaskList();

        verify(mockOnTaskListGatewayListener).onTaskListLoad((Collection<TaskEntitity>) argThat(hasSize(0)));
    }

    @Test
    public void loadOneElementTaskList() throws Exception {
        String expectedKey = "1";
        expectedTasks.put(expectedKey, new TaskEntitity(expectedKey, "title", "description"));

        taskListInMemory.loadTaskList();

        verify(mockOnTaskListGatewayListener).onTaskListLoad((Collection<TaskEntitity>) argThat(hasSize(1)));
    }
}
