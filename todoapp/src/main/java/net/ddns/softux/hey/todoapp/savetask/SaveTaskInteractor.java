package net.ddns.softux.hey.todoapp.savetask;

public class SaveTaskInteractor implements SaveTaskUseCase {
    protected SaveTaskGateway saveTaskGateway;
    protected OnSaveTaskListener onSaveTaskListener;

    public SaveTaskInteractor(SaveTaskGateway saveTaskGateway, OnSaveTaskListener onSaveTaskListener) {
        this.saveTaskGateway = saveTaskGateway;
        this.onSaveTaskListener = onSaveTaskListener;
    }

    @Override
    public void save(Task task) {
        saveTaskGateway.save(task, savedTask -> onSaveTaskListener.onSavedTask(savedTask));
    }
}