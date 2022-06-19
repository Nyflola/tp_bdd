package insa.tp.models;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "users")
public class MongoDBUser {


    @Indexed(unique = true)
    @Field(value="_id")
    @MongoId
    private ObjectId id;
    @Field(value="login")
    private String login;
    @Field(value="password")
    private String password;
    @Field(value="firstname")
    private String firstname;
    @Field(value="lastname")
    private String lastname;
    @Field(value="picture")
    private String picture;
    @Field(value="description")
    private String description;

    public MongoDBUser(){

    }

    public MongoDBUser(String login, String password) {
        this.login = login;
        this.password = password;
        this.firstname = "";
        this.lastname = "";
        this.picture = "";
        this.description = "";
    }

    public MongoDBUser(String login, String password, String firstname, String lastname, String picture, String description) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.picture = picture;
        this.description = description;
    }

    public ObjectId getId() {
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

    public void setId(ObjectId id) {
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
