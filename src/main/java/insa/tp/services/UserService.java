package insa.tp.services;
import insa.tp.models.User;
import insa.tp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.ws.rs.GET;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
