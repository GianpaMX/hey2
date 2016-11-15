package net.ddns.softux.hey.tests.todoapp.tasklist.data;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;
import net.ddns.softux.hey.todoapp.tasklist.data.TaskListRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TaskListRepositoryTest {
    @Mock
    public TaskListGateway.OnTaskListGatewayListener mockOnTaskListGatewayListener;

    public TaskListGateway[] taskListGateway;

    private TaskListRepository taskListRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        taskListGateway = new TaskListGateway[3];
        for (int i = 0; i < 3; i++) {
            taskListGateway[i] = mock(TaskListGateway.class);
        }

        taskListRepository = new TaskListRepository(taskListGateway);
        taskListRepository.setOnTaskListGatewayListener(mockOnTaskListGatewayListener);
    }

    @Test
    public void loadTaskList() {
        InOrder inOrder = Mockito.inOrder(taskListGateway[0], taskListGateway[1], taskListGateway[2]);

        taskListRepository.loadTaskList();

        inOrder.verify(taskListGateway[0]).loadTaskList();
        inOrder.verify(taskListGateway[1]).loadTaskList();
        inOrder.verify(taskListGateway[2]).loadTaskList();
    }

    @Test
    public void setEachOnTaskListGatewayListener() {
        verify(taskListGateway[0]).setOnTaskListGatewayListener(taskListRepository);
        verify(taskListGateway[1]).setOnTaskListGatewayListener(taskListRepository);
        verify(taskListGateway[2]).setOnTaskListGatewayListener(taskListRepository);
    }

    @Test
    public void onTaskListLoad() {
        for (int i = 0; i < 3; i++) {
            taskListRepository.onTaskListLoad(new ArrayList<TaskEntitity>(), taskListGateway[i]);
        }

        verify(mockOnTaskListGatewayListener, times(3)).onTaskListLoad(any(Collection.class), any(TaskListGateway.class));
    }

    @Test
    public void onTaskAdded() {
        for (int i = 0; i < 3; i++) {
            taskListRepository.onTaskAdded(new TaskEntitity(), taskListGateway[i]);
        }

        verify(mockOnTaskListGatewayListener, times(3)).onTaskAdded(any(TaskEntitity.class), any(TaskListGateway.class));
    }
}
