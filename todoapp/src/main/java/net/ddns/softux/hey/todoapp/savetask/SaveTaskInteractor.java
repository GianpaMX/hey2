package net.ddns.softux.hey.todoapp.savetask;

public class SaveTaskInteractor implements SaveTaskUseCase {
    protected SaveTaskGateway saveTaskGateway;

    public SaveTaskInteractor(SaveTaskGateway saveTaskGateway) {
        this.saveTaskGateway = saveTaskGateway;
    }

    @Override
    public void save(Task task, final OnSaveTaskListener onSaveTaskListener) {
        saveTaskGateway.save(task, new SaveTaskGatewayCallback() {
            @Override
            public void onSuccess(TaskEntitity savedTask) {
                onSaveTaskListener.onSavedTask(savedTask.toModel());
            }
        });
    }
}
