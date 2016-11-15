package net.ddns.softux.hey.tests.todoapp.tasklist;

import net.ddns.softux.hey.todoapp.task.Task;
import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.tasklist.OnTaskListLoadListener;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;
import net.ddns.softux.hey.todoapp.tasklist.TaskListInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;

public class TaskListInteractorTest {

    @Mock
    public TaskListGateway mockTaskListGateway;

    @Mock
    public OnTaskListLoadListener mockOnTaskListLoadListener;

    public TaskListInteractor taskListInteractor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        taskListInteractor = new TaskListInteractor(mockTaskListGateway);
        taskListInteractor.start(mockOnTaskListLoadListener);
    }

    @Test
    public void start() throws Exception {
        verify(mockTaskListGateway).loadTaskList();
    }

    @Test
    public void onTaskListLoad() throws Exception {
        ArrayList<TaskEntitity> expectedTaskEntitities = new ArrayList<>();
        expectedTaskEntitities.add(new TaskEntitity());

        taskListInteractor.onTaskListLoad(expectedTaskEntitities, any(TaskListGateway.class));

        verify(mockOnTaskListLoadListener).onTaskListLoad((List<Task>) argThat(hasSize(1)));
    }

    @Test
    public void onTaskAdded() {
        taskListInteractor.onTaskAdded(new TaskEntitity(), mockTaskListGateway);

        verify(mockOnTaskListLoadListener).onTaskAdded(any(Task.class));
    }
}