package ks.dev.todo_list_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ks.dev.todo_list_api.model.Task;
import ks.dev.todo_list_api.service.Task.ITaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final ITaskService taskService;
    

    @PostMapping("/{userId}/create-task")
    public ResponseEntity<?> createTask(@PathVariable String userId, @RequestBody Task task){
        try{
            Task newTask = taskService.createTask(task,userId);

            return ResponseEntity.ok(newTask);
        }catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(500).body("An error occurred while creating for the user.");
            }
        
    }

    @PutMapping("/{idTask}/update")
    public ResponseEntity<?> updateTask(@PathVariable String idTask , @RequestBody Task newTask)
    {
        try{
            Task task = taskService.updateTask(newTask, idTask);
            return ResponseEntity.ok(task);
            
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while updating for the user.");
        }
    }
    @GetMapping("/all")
    public List<Task> getAllTasks (){
        
     return taskService.getAllTasks();
       
    }

    @GetMapping("/{taskId}/search")
    public ResponseEntity<?> searchTask(@PathVariable String taskId) {
        try {
            Task task = taskService.getTaskById(taskId); // Hypothetical service method
            if (task != null) {
                return ResponseEntity.ok(task);
            } else {
                return ResponseEntity.status(404).body("Task not found");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
    

    @GetMapping("/{priority}/search/priority")
    public ResponseEntity<?> searchTaskByPriority(@PathVariable String priority){
        try{
           List<Task> tasks = taskService.getTasksByPriority(priority);
            return ResponseEntity.ok(tasks);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while searching task.");
        }

    }

    @GetMapping("/{status}/search/status")
    public ResponseEntity<?> searchTaskByStatus(@PathVariable String status){
        try{
           List<Task> tasks = taskService.getTasksByStatus(status);
            return ResponseEntity.ok(tasks);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while searching task.");
        }

    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<?> searchTasksByUserId(@PathVariable String userId){
        try{
           List<Task> tasks = taskService.getTasksByUserId(userId);
            return ResponseEntity.ok(tasks);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while searching task.");
        }

    }

    @GetMapping("/{title}/search/title")
    public ResponseEntity<?> searchTaskByTitle(@PathVariable String title){
        try{
           List<Task> tasks = taskService.searchTasksByTitle(title);
            return ResponseEntity.ok(tasks);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while searching task.");
        }

    }

    @DeleteMapping("/{taskId}/delete")
    public ResponseEntity<?> deleteTask(@PathVariable String taskId) {
        try {
            
            boolean isDeleted = taskService.deleteTask(taskId); 
            if (isDeleted) {
                return ResponseEntity.ok("Task with ID " + taskId + " has been deleted successfully.");
            } else {
                return ResponseEntity.status(404).body("Task with ID " + taskId + " not found.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while deleting the task.");
        }
    }
    

}


