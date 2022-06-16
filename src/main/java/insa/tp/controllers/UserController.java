package insa.tp.controllers;

import insa.tp.models.User;
import insa.tp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/get/{login}")
    public User getUserByLogin(@PathVariable String login){
        return userService.getUserByLogin(login);
    }
}
