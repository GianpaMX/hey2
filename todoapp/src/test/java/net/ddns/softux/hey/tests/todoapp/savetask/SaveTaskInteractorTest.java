package net.ddns.softux.hey.tests.todoapp.savetask;

import net.ddns.softux.hey.todoapp.data.Task;
import net.ddns.softux.hey.todoapp.data.TaskRepository;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskInteractor;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;

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

public class SaveTaskInteractorTest {

    @Mock
    public SaveTaskUseCase.Callback mockCallback;

    @Mock
    public TaskRepository taskRepository;

    public SaveTaskUseCase saveTaskUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        saveTaskUseCase = new SaveTaskInteractor(taskRepository);
    }

    @Test
    public void saveTaskGatewayisCalled() {
        saveTaskUseCase.save(mock(Task.class), mockCallback);

        verify(taskRepository).persist(any(Task.class), any(TaskRepository.Callback.class));
    }

    @Test
    public void saveTaskDeliverResult() {
        saveTaskUseCase.save(mock(Task.class), mockCallback);
        ArgumentCaptor<TaskRepository.Callback> saveTaskGatewayCallbackArgumentCaptor = ArgumentCaptor.forClass(TaskRepository.Callback.class);
        verify(taskRepository).persist(any(Task.class), saveTaskGatewayCallbackArgumentCaptor.capture());

        saveTaskGatewayCallbackArgumentCaptor.getValue().onSuccess(new Task());

        verify(mockCallback).onSuccess(any(Task.class));
    }

    @Test
    public void checkTask() {
        saveTaskUseCase.check(new Task(), null);

        verify(taskRepository).persist((Task) argThat(hasField("checked", equalTo(true))), any(TaskRepository.Callback.class));
    }

    @Test
    public void uncheckTask() {
        Task task = new Task();
        task.checked = true;

        saveTaskUseCase.uncheck(task, null);

        verify(taskRepository).persist((Task) argThat(hasField("checked", equalTo(false))), any(TaskRepository.Callback.class));
    }
}
