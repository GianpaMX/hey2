package net.ddns.softux.hey.todoapp.savetask;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 30/06/16.
 */
public class SaveTaskInteractorTest {
    @Test
    public void saveTask() {
        Task task = new Task("Title", "Description");
        Task savedTask = new Task("key", "Title", "Description");

        SaveTaskGateway mockSaveTaskGateway = mock(SaveTaskGateway.class);
        OnSaveTaskListener mockCallback = mock(OnSaveTaskListener.class);

        ArgumentCaptor<SaveTaskGatewayCallback> saveTaskGatewayCallback = ArgumentCaptor.forClass(SaveTaskGatewayCallback.class);
        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);

        SaveTaskUseCase saveTaskUseCase = new SaveTaskInteractor(mockSaveTaskGateway, mockCallback);

        saveTaskUseCase.save(task);
        verify(mockSaveTaskGateway).save(any(Task.class), saveTaskGatewayCallback.capture());
        saveTaskGatewayCallback.getValue().onSuccess(savedTask);
        verify(mockCallback).onSavedTask(taskArgumentCaptor.capture());

        assertNotNull("Key should have a value now", taskArgumentCaptor.getValue().key);
    }
}
