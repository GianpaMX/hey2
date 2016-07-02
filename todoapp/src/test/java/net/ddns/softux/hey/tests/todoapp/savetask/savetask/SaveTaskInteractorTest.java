package net.ddns.softux.hey.tests.todoapp.savetask.savetask;

import net.ddns.softux.hey.todoapp.savetask.OnSaveTaskListener;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskGateway;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskGatewayCallback;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskInteractor;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;
import net.ddns.softux.hey.todoapp.savetask.Task;
import net.ddns.softux.hey.todoapp.savetask.TaskEntitity;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 30/06/16.
 */
public class SaveTaskInteractorTest {
    @Test
    public void saveTaskGatewayisCalled() {
        SaveTaskGateway mockSaveTaskGateway = mock(SaveTaskGateway.class);

        SaveTaskUseCase saveTaskUseCase = new SaveTaskInteractor(mockSaveTaskGateway);
        saveTaskUseCase.save(mock(Task.class), mock(OnSaveTaskListener.class));

        verify(mockSaveTaskGateway).save(any(Task.class), any(SaveTaskGatewayCallback.class));
    }

    @Test
    public void saveTaskDeliverResult() {
        SaveTaskGateway mockSaveTaskGateway = mock(SaveTaskGateway.class);
        OnSaveTaskListener mockOnSaveTaskListener = mock(OnSaveTaskListener.class);

        SaveTaskUseCase saveTaskUseCase = new SaveTaskInteractor(mockSaveTaskGateway);
        saveTaskUseCase.save(mock(Task.class), mockOnSaveTaskListener);

        ArgumentCaptor<SaveTaskGatewayCallback> saveTaskGatewayCallbackArgumentCaptor = ArgumentCaptor.forClass(SaveTaskGatewayCallback.class);
        verify(mockSaveTaskGateway).save(any(Task.class), saveTaskGatewayCallbackArgumentCaptor.capture());
        saveTaskGatewayCallbackArgumentCaptor.getValue().onSuccess(new TaskEntitity());

        verify(mockOnSaveTaskListener).onSavedTask(any(Task.class));
    }
}