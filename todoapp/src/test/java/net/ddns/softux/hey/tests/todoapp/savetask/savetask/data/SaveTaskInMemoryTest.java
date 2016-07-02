package net.ddns.softux.hey.tests.todoapp.savetask.savetask.data;

import net.ddns.softux.hey.todoapp.savetask.SaveTaskGatewayCallback;
import net.ddns.softux.hey.todoapp.savetask.Task;
import net.ddns.softux.hey.todoapp.savetask.TaskEntitity;
import net.ddns.softux.hey.todoapp.savetask.data.SaveTaskInMemory;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by juan on 30/06/16.
 */
public class SaveTaskInMemoryTest {
    @Test
    public void save() {
        SaveTaskGatewayCallback mockSaveTaskGatewayCallback = mock(SaveTaskGatewayCallback.class);
        ArgumentCaptor<TaskEntitity> argumentCaptor = ArgumentCaptor.forClass(TaskEntitity.class);

        SaveTaskInMemory saveTaskInMemory = new SaveTaskInMemory();
        saveTaskInMemory.save(new Task(), mockSaveTaskGatewayCallback);

        verify(mockSaveTaskGatewayCallback).onSuccess(argumentCaptor.capture());
        assertNotNull("A saved task should have a key", argumentCaptor.getValue().key);
    }

    @Test
    public void saveExistingTask() {
        final String existingKey = "1";
        TaskEntitity existingTask = new TaskEntitity(existingKey, "title", "description");
        Map<String, TaskEntitity> mockMap = mock(Map.class);
        SaveTaskGatewayCallback mockSaveTaskGatewayCallback = mock(SaveTaskGatewayCallback.class);
        when(mockMap.containsKey(existingKey)).thenReturn(true);
        when(mockMap.get(existingKey)).thenReturn(existingTask);

        SaveTaskInMemory saveTaskInMemory = new SaveTaskInMemory(mockMap, 1);
        saveTaskInMemory.save(existingTask.toModel(), mockSaveTaskGatewayCallback);

        verify(mockSaveTaskGatewayCallback).onSuccess(eq(existingTask));
    }
}
