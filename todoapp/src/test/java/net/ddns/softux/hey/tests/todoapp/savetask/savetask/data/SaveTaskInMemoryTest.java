package net.ddns.softux.hey.tests.todoapp.savetask.savetask.data;

import net.ddns.softux.hey.todoapp.savetask.SaveTaskGatewayCallback;
import net.ddns.softux.hey.todoapp.savetask.Task;
import net.ddns.softux.hey.todoapp.savetask.TaskEntitity;
import net.ddns.softux.hey.todoapp.savetask.data.SaveTaskInMemory;

import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 30/06/16.
 */
public class SaveTaskInMemoryTest {
    @Test
    public void save() throws Exception {
        SaveTaskGatewayCallback mockSaveTaskGatewayCallback = mock(SaveTaskGatewayCallback.class);

        SaveTaskInMemory saveTaskInMemory = new SaveTaskInMemory();
        saveTaskInMemory.save(mock(Task.class), mockSaveTaskGatewayCallback);

        verify(mockSaveTaskGatewayCallback).onSuccess(any(TaskEntitity.class));
    }
}