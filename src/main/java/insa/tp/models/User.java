package insa.tp.models;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "users")
public class User {

    private @Id Long id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String picture;
    private String description;

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(Long id, String login, String password, String firstname, String lastname, String picture, String description) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.picture = picture;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
