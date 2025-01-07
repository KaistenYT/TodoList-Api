package ks.dev.todo_list_api.service.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import ks.dev.todo_list_api.model.Task;
import ks.dev.todo_list_api.repository.TaskRepository;
import ks.dev.todo_list_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public Task createTask(Task task, String userId) {
        // Check if the user exists
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }
    
        // Set additional fields for the task
        task.setUserId(userId);
        task.setCreatedAt(LocalDateTime.now());
    
        // Save and return the task
        return taskRepository.save(task);
    }
    


    @Override
    public Task updateTask(Task task, String id) {
        Task existingTask = taskRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setCategory(task.getCategory());
        existingTask.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(existingTask);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(String id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Override
    public List<Task> getTasksByPriority(String priority) {
        return taskRepository.findByPriority(priority);
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> getTasksByUserId(String userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public List<Task> searchTasksByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
 
    @Override
    public boolean deleteTask(String taskId) {
        Optional<Task> task = taskRepository.findById(taskId); // Assuming taskRepository exists
        if (task.isPresent()) {
            taskRepository.delete(task.get());
            return true;
        }
        return false;
    }
    

    @Override
public List<Task> getTasksByUser(String userId) {
    if (!userRepository.existsById(userId)) {
        throw new IllegalArgumentException("User not found");
    }
    return taskRepository.findByUserId(userId);
}
@Override
public void deleteTasksByUser(String userId) {
    if (!userRepository.existsById(userId)) {
        throw new IllegalArgumentException("User not found");
    }
    taskRepository.deleteByUserId(userId);
}
}
