package ks.dev.todo_list_api.service.user;

import java.util.List;
import java.util.Optional;

import ks.dev.todo_list_api.model.User;
import ks.dev.todo_list_api.request.CreateUserRequest;
import ks.dev.todo_list_api.request.UpdateUserRequest;

public interface IUserService {

    User registerUser(CreateUserRequest request);
    List<User> listUsers();
    User updateUser (UpdateUserRequest request , String id);
    Optional<User> findUserById(String id);
    User findByUsername(String username);
    List<User> findByEmail(String email);
    void deleteUser(String id);


}
