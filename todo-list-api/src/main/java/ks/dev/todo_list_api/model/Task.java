package ks.dev.todo_list_api.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;



import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

     @Id
    private String id;
     @NotBlank(message = "title is required")
     private String title;
    private String description;
    private String category;
    private String priority;
    private String status;
     @CreatedDate
    private LocalDateTime createdAt;
     @LastModifiedDate
    private LocalDateTime updatedAt;
    private String userId; 



}
