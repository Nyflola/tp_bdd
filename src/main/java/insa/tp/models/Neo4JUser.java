package insa.tp.models;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Node
public class Neo4JUser {
    @Id
    @Property(name = "login")
    private String login;
    @Relationship(type = "FRIEND")
    List<Neo4JUser> friends;

    public Neo4JUser() {
    }

    public Neo4JUser(String login) {
        this.login = login;
        this.friends = new ArrayList<>();
    }

    public Neo4JUser(String login, List<Neo4JUser> friends) {
        this.login = login;
        this.friends = friends;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Neo4JUser> getFriends() {
        return friends;
    }

    public void setFriends(List<Neo4JUser> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neo4JUser neo4JUser = (Neo4JUser) o;
        return Objects.equals(login, neo4JUser.login);
    }

    @Override
    public String toString() {
        return "login: " +login ;
    }
}
