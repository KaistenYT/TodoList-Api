package ks.dev.todo_list_api.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
