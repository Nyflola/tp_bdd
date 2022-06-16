package insa.tp.repositories;

import insa.tp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository {
    User findById(long id);

    User findByLogin(String login);

    List<User> findAll();
}