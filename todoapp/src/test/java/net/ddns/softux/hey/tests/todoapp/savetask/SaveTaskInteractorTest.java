package net.ddns.softux.hey.tests.todoapp.savetask;

import net.ddns.softux.hey.todoapp.savetask.OnSaveTaskListener;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskGateway;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskGatewayCallback;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskInteractor;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;
import net.ddns.softux.hey.todoapp.task.Task;
import net.ddns.softux.hey.todoapp.task.TaskEntitity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static net.ddns.softux.tests.utils.fields.HasFieldWithValue.hasField;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by juan on 30/06/16.
 */
public class SaveTaskInteractorTest {

    @Mock
    public OnSaveTaskListener mockOnSaveTaskListener;

    @Mock
    public SaveTaskGateway mockSaveTaskGateway;

    public SaveTaskUseCase saveTaskUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        saveTaskUseCase = new SaveTaskInteractor(mockSaveTaskGateway);
    }

    @Test
    public void saveTaskGatewayisCalled() {
        saveTaskUseCase.save(mock(Task.class), mockOnSaveTaskListener);

        verify(mockSaveTaskGateway).save(any(Task.class), any(SaveTaskGatewayCallback.class));
    }

    @Test
    public void saveTaskDeliverResult() {
        saveTaskUseCase.save(mock(Task.class), mockOnSaveTaskListener);

        ArgumentCaptor<SaveTaskGatewayCallback> saveTaskGatewayCallbackArgumentCaptor = ArgumentCaptor.forClass(SaveTaskGatewayCallback.class);
        verify(mockSaveTaskGateway).save(any(Task.class), saveTaskGatewayCallbackArgumentCaptor.capture());
        saveTaskGatewayCallbackArgumentCaptor.getValue().onSuccess(new TaskEntitity());

        verify(mockOnSaveTaskListener).onSavedTask(any(Task.class));
    }

    @Test
    public void checkTask() {
        saveTaskUseCase.check(new Task(), mockOnSaveTaskListener);

        verify(mockSaveTaskGateway).save((Task) argThat(hasField("checked", equalTo(true))), any(SaveTaskGatewayCallback.class));
    }
}
