package insa.tp.controllers;

import insa.tp.MongoDBUserRepository;
import insa.tp.Neo4JUserRepository;
import insa.tp.models.MongoDBUser;
import insa.tp.models.Neo4JUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("relations")
public class RelationController {
    @Autowired
    private MongoDBUserRepository mongoDBUserRepository;
    @Autowired
    private Neo4JUserRepository neo4JUserRepository;

    @GET
    @Path("get/friends")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByLogin(@QueryParam("login") String login){
        Optional<MongoDBUser> opt = mongoDBUserRepository.findByLogin(login);
        if (opt.isEmpty()) return Response.status(Response.Status.NOT_FOUND).build();
        Optional<Neo4JUser> opt2 = neo4JUserRepository.findById(login);
        if (opt2.isEmpty()) return Response.status(Response.Status.NOT_FOUND).build();
        Neo4JUser user = opt2.get();
        List<String> friends = new ArrayList<>();
        for(Neo4JUser u : user.getFriends()) friends.add(u.getLogin());
        return Response.ok(friends).build();
    }

    @GET
    @Path("neo4")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNodes(){
        List<MongoDBUser> opt = mongoDBUserRepository.findAll();
        List<Neo4JUser> opt2 = neo4JUserRepository.findAll();
        Neo4JUser nouveau;
        List<Neo4JUser> res = new ArrayList<>();
        if(opt.isEmpty()) return Response.status(Response.Status.NOT_FOUND).build();
        for(MongoDBUser user : opt){
            boolean in = false;
            for(Neo4JUser neo: opt2){
                if(neo.getLogin() == user.getLogin()){
                    in = true;
                }
            }
            if(!in){
                nouveau = new Neo4JUser(user.getLogin());
                res.add(nouveau);
                neo4JUserRepository.save(nouveau);
            }
        }
        return Response.ok(res).build();
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRelation(@QueryParam("login") String login,@QueryParam("login2") String login2){
        Optional<Neo4JUser> opt1 = neo4JUserRepository.findById(login);
        Optional<Neo4JUser> opt2 = neo4JUserRepository.findById(login2);
        if (opt1.isEmpty() || opt2.isEmpty()) return Response.status(Response.Status.NOT_FOUND).build();

        Neo4JUser user1 = opt1.get();
        Neo4JUser user2 = opt2.get();

        user1.getFriends().add(user2);
        user2.getFriends().add(user1);
        neo4JUserRepository.save(user1);
        neo4JUserRepository.save(user2);
        return Response.ok().build();
    }

    @DELETE
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRelation(@QueryParam("login") String login,@QueryParam("login2") String login2) {
        Optional<Neo4JUser> opt1 = neo4JUserRepository.findById(login);
        Optional<Neo4JUser> opt2 = neo4JUserRepository.findById(login2);
        if (opt1.isEmpty() || opt2.isEmpty()) return Response.status(Response.Status.NOT_FOUND).build();
        neo4JUserRepository.removeFriend(opt1.get().getLogin(),opt2.get().getLogin());
        return Response.ok().build();
    }
}
