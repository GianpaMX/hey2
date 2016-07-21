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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 20/07/16.
 */

public class TaskListRepositoryTest {
    @Mock
    public TaskListGateway.OnTaskListGatewayListener mockOnTaskListGatewayListener;

    @Mock
    public TaskListGateway taskListGateway1;

    @Mock
    public TaskListGateway taskListGateway2;

    @Mock
    public TaskListGateway taskListGateway3;

    private TaskListRepository taskListRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        taskListRepository = new TaskListRepository(taskListGateway1, taskListGateway2, taskListGateway3);
        taskListRepository.setOnTaskListGatewayListener(mockOnTaskListGatewayListener);
    }

    @Test
    public void loadTaskList() {
        InOrder inOrder = Mockito.inOrder(taskListGateway1, taskListGateway2, taskListGateway3);

        taskListRepository.loadTaskList();

        inOrder.verify(taskListGateway1).loadTaskList();
        inOrder.verify(taskListGateway2).loadTaskList();
        inOrder.verify(taskListGateway3).loadTaskList();
    }

    @Test
    public void setEachOnTaskListGatewayListener() {
        verify(taskListGateway1).setOnTaskListGatewayListener(taskListRepository);
        verify(taskListGateway2).setOnTaskListGatewayListener(taskListRepository);
        verify(taskListGateway3).setOnTaskListGatewayListener(taskListRepository);
    }

    @Test
    public void onTaskListLoad() {
        for(int i = 0 ; i< 3; i ++) {
            taskListRepository.onTaskListLoad(new ArrayList<TaskEntitity>());
        }

        verify(mockOnTaskListGatewayListener, times(3)).onTaskListLoad(any(Collection.class));
    }

    @Test
    public void onTaskAdded() {
        for(int i = 0 ; i< 3; i ++) {
            taskListRepository.onTaskAdded(new TaskEntitity());
        }

        verify(mockOnTaskListGatewayListener, times(3)).onTaskAdded(any(TaskEntitity.class));
    }
}
