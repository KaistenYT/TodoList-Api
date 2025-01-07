package ks.dev.todo_list_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ks.dev.todo_list_api.model.Task;
import java.util.List;
import java.time.LocalDateTime;


public interface TaskRepository extends MongoRepository<Task , String> {

    List<Task> findByCreatedAt(LocalDateTime createdAt);
    List<Task> findByTitle(String titile);
    List<Task> findByPriority(String priority);
    List<Task> findByStatus(String status);
    List<Task> findByUserId(String userId);
    List<Task> findByTitleContainingIgnoreCase(String title);
    void deleteByUserId(String userId);


}
