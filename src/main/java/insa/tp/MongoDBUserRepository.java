package insa.tp;

import insa.tp.models.MongoDBUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoDBUserRepository extends MongoRepository<MongoDBUser, ObjectId> {
    Optional<MongoDBUser> findByLogin(String login);
}