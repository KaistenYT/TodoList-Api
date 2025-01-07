package ks.dev.todo_list_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ks.dev.todo_list_api.model.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User , String> {

    User findByUsername(String username);
    List<User> findByEmail(String email);

    @SuppressWarnings("null")
    Optional<User> findById( String id);

}
