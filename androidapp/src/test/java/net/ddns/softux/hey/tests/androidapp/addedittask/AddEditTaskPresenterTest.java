package net.ddns.softux.hey.tests.androidapp.addedittask;

import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskPresenter;
import net.ddns.softux.hey.androidapp.addedittask.AddEditTaskView;
import net.ddns.softux.hey.androidapp.task.TaskViewModel;
import net.ddns.softux.hey.todoapp.data.Task;
import net.ddns.softux.hey.todoapp.savetask.SaveTaskUseCase;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddEditTaskPresenterTest {

    private SaveTaskUseCase saveTaskUseCase;
    private AddEditTaskPresenter addEditTaskPresenter;
    private AddEditTaskView view;

    @Before
    public void setUp() {
        saveTaskUseCase = mock(SaveTaskUseCase.class);
        view = mock(AddEditTaskView.class);

        addEditTaskPresenter = new AddEditTaskPresenter(saveTaskUseCase);
        addEditTaskPresenter.setView(view);
    }

    @Test
    public void save() {
        addEditTaskPresenter.save(new TaskViewModel());

        verify(saveTaskUseCase).save(any(Task.class), any(SaveTaskUseCase.Callback.class));
    }
}
