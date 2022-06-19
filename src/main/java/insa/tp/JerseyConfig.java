package insa.tp;
import insa.tp.controllers.RelationController;
import insa.tp.controllers.UserController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        register(UserController.class);
        register(RelationController.class);
    }
}
