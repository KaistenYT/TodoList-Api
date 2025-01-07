package ks.dev.todo_list_api.request;

import lombok.Data;

@Data
public class UpdateUserRequest {


    private String username;
    private String firstName;
    private String lastName;
}
