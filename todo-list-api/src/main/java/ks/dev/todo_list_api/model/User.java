package ks.dev.todo_list_api.model;




import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Users")
@CompoundIndexes({
    @CompoundIndex(name = "username_idx", def = "{'username: 1'}", unique = true),
    @CompoundIndex(name = "email_idx", def = "{'email: 1'}", unique = true),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    

    @Id
    private String id;
    @NotBlank(message = "username is required")
    private String  username; 
    private String firstName;
    private String lastName;
    @NotBlank(message = "email is required")
    @Email(message = "email should be valid")
    private String email; 
    @NotBlank(message = "password is required")
    @Size(min=8 , message = "password should have at least 8 characters")
    private String password; 
  




}
