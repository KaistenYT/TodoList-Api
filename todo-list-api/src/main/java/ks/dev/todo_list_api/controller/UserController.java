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

import ks.dev.todo_list_api.model.User;
import ks.dev.todo_list_api.request.CreateUserRequest;
import ks.dev.todo_list_api.request.UpdateUserRequest;
import ks.dev.todo_list_api.service.user.IUserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest request) {
        try {
            User user = userService.registerUser(request);
            return ResponseEntity.ok(user); 
        } catch (IllegalArgumentException e) {
            
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
           
            return ResponseEntity.status(500).body("An error occurred while creating the user.");
        }
    }
    
    @PutMapping("/{idUser}/update")
    public ResponseEntity<?> updateUser(@PathVariable String idUser, @RequestBody UpdateUserRequest request){
        try{
            User thUser = userService.updateUser(request, idUser);
            return ResponseEntity.ok(thUser);
        } catch (IllegalArgumentException e) {
            
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
           
            return ResponseEntity.status(500).body("An error occurred while updating the user.");
        }
    }

    @DeleteMapping("/{idUser}/delete")
    public ResponseEntity<String> DeleteUser(@PathVariable String idUser){

        try{
            userService.deleteUser(idUser);
            return ResponseEntity.ok("User deleted");
        } catch (IllegalArgumentException e) {
            
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
           
            return ResponseEntity.status(500).body("An error occurred while deleting the user.");
        }

    }
    @GetMapping("/{userEmail}/search")
    public ResponseEntity<?> findByEmail(@PathVariable String userEmail) {
        try {
            List<User> users = userService.findByEmail(userEmail);
            if (users.isEmpty()) {
                return ResponseEntity.status(404).body("User not found.");
            }
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while searching for the user.");
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        try {
            User user = userService.findByUsername(username);
            if (user == null) {
                return ResponseEntity.status(404).body("User not found.");
            }
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while searching for the user.");
        }
    }

    @GetMapping
public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.listUsers();
    return ResponseEntity.ok(users);
}

}
