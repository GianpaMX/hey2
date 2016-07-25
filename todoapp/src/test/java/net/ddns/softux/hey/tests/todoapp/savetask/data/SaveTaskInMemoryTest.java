package net.ddns.softux.hey.tests.todoapp.savetask.data;

import net.ddns.softux.hey.todoapp.savetask.SaveTaskGatewayCallback;
import net.ddns.softux.hey.todoapp.savetask.data.SaveTaskInMemory;
import net.ddns.softux.hey.todoapp.task.Task;
import net.ddns.softux.hey.todoapp.task.TaskEntitity;
import net.ddns.softux.hey.todoapp.tasklist.TaskListGateway;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static net.ddns.softux.tests.utils.fields.HasFieldWithValue.hasField;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by juan on 30/06/16.
 */
public class SaveTaskInMemoryTest {

    @Mock
    public SaveTaskGatewayCallback mockSaveTaskGatewayCallback;

    public SaveTaskInMemory saveTaskInMemory;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        saveTaskInMemory = new SaveTaskInMemory();
    }

    @Test
    public void save() {
        SaveTaskGatewayCallback mockSaveTaskGatewayCallback = mock(SaveTaskGatewayCallback.class);
        ArgumentCaptor<TaskEntitity> argumentCaptor = ArgumentCaptor.forClass(TaskEntitity.class);

        saveTaskInMemory.save(new Task(), mockSaveTaskGatewayCallback);

        verify(mockSaveTaskGatewayCallback).onSuccess(argumentCaptor.capture());
        assertNotNull("A saved task should have a key", argumentCaptor.getValue().key);
        assertEquals("Size should be 1 now", 1, saveTaskInMemory.size());
    }

    @Test
    public void saveExistingTask() {
        final String existingKey = "1";
        TaskEntitity existingTask = new TaskEntitity(existingKey, "title", "description");
        Map<String, TaskEntitity> mockMap = mock(Map.class);
        when(mockMap.containsKey(existingKey)).thenReturn(true);
        when(mockMap.get(existingKey)).thenReturn(existingTask);
        SaveTaskInMemory saveTaskInMemory = new SaveTaskInMemory(mockMap, 1);

        saveTaskInMemory.save(existingTask.toModel(), mockSaveTaskGatewayCallback);

        verify(mockSaveTaskGatewayCallback).onSuccess(eq(existingTask));
    }

    @Test
    public void taskListGatewayListener() {
        TaskListGateway.OnTaskListGatewayListener mockOnTaskListGatewayListener = mock(TaskListGateway.OnTaskListGatewayListener.class);
        saveTaskInMemory.setOnTaskListGatewayListener(mockOnTaskListGatewayListener);

        saveTaskInMemory.save(new Task(), mock(SaveTaskGatewayCallback.class));

        verify(mockOnTaskListGatewayListener).onTaskAdded(any(TaskEntitity.class), any(TaskListGateway.class));
    }

    @Test
    public void saveCheckedTask() {
        Task checkedTask = new Task();
        checkedTask.checked = true;

        saveTaskInMemory.save(checkedTask, mockSaveTaskGatewayCallback);

        verify(mockSaveTaskGatewayCallback).onSuccess((TaskEntitity) argThat(hasField("checked", equalTo(true))));
    }
}
