package ks.dev.todo_list_api.service.Task;

import java.util.List;
import ks.dev.todo_list_api.model.Task;

public interface ITaskService {

    Task createTask(Task task, String userId);
    Task updateTask(Task task, String id);
    List<Task> getAllTasks();
    Task getTaskById(String id);
    List<Task> getTasksByPriority(String priority);
    List<Task> getTasksByStatus(String status);
    List<Task> getTasksByUserId(String userId);
    List<Task> searchTasksByTitle(String title);
    public boolean deleteTask(String taskId);
    List<Task> getTasksByUser(String userId);
    void deleteTasksByUser(String userId);

}
