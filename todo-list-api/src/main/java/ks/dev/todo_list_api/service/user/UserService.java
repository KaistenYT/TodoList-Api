package ks.dev.todo_list_api.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ks.dev.todo_list_api.model.User;
import ks.dev.todo_list_api.repository.UserRepository;
import ks.dev.todo_list_api.request.CreateUserRequest;
import ks.dev.todo_list_api.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User registerUser(CreateUserRequest request) {
       User user = new User();
       user.setUsername(request.getUsername());
       user.setFirstName(request.getFirstName());
       user.setLastName(request.getLastName());
       user.setEmail(request.getEmail());
       user.setPassword(request.getPassword());
       return userRepository.save(user);
    }

    @Override
    public List<User> listUsers() {
       return userRepository.findAll();
    }

    @Override
    public User updateUser(UpdateUserRequest request, String id) {
        User theUser = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
        theUser.setUsername(request.getUsername());
        theUser.setFirstName(request.getFirstName());
        theUser.setLastName(request.getLastName());
        return userRepository.save(theUser);
    }
    

    @Override
    public Optional<User> findUserById(String id) {
       return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
       return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findByEmail(String email) {
       return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(String id) {
        User theUser = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    
        userRepository.delete(theUser); 
    }
    
    

}
