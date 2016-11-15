package net.ddns.softux.hey.tests.todoapp.tasklist.data;

import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;
import net.ddns.softux.hey.todoapp.tasklist.data.TaskListInFile;
import net.ddns.softux.hey.todoapp.utils.ObjectFile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskListInFileTest {
    @Mock
    private ObjectFile mockObjectFile;

    @Mock
    public TaskListGateway.OnTaskListGatewayListener mockOnTaskListGatewayListener;
    private TaskListInFile taskListInFile;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        taskListInFile = new TaskListInFile(mockObjectFile);
        taskListInFile.setOnTaskListGatewayListener(mockOnTaskListGatewayListener);
    }

    @Test
    public void saveTaskList() throws IOException {
        taskListInFile.saveTaskList();

        verify(mockObjectFile).writeObject(any(Collection.class));
        verify(mockOnTaskListGatewayListener).onTaskListSaved(taskListInFile);
    }

    @Test
    public void loadTaskList() throws IOException, ClassNotFoundException {
        when(mockObjectFile.readObject()).thenReturn(new HashMap<String, TaskEntitity>());

        taskListInFile.loadTaskList();

        verify(mockObjectFile).readObject();
        verify(mockOnTaskListGatewayListener).onTaskListLoad((Collection<TaskEntitity>) argThat(hasSize(0)), any(TaskListGateway.class));
    }
}
