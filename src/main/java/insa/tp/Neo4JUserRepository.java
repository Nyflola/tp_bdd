package insa.tp;

import insa.tp.models.Neo4JUser;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Neo4JUserRepository extends Neo4jRepository<Neo4JUser, String> {
    @Query("MATCH (:Neo4JUser {login: $username1})-[r:FRIEND]-(:Neo4JUser {login: $username2}),(:Neo4JUser {login: $username2})-[e:FRIEND]-(:Neo4JUser {login: $username1}) DELETE r DELETE e")
    List<String> removeFriend(String username1, String username2);
}