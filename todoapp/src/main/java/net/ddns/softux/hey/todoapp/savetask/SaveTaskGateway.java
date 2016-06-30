package net.ddns.softux.hey.todoapp.savetask;

/**
 * Created by juan on 30/06/16.
 */
public interface SaveTaskGateway {
    void save(Task task, SaveTaskGatewayCallback callback);
}
