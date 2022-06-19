package insa.tp.controllers;

import insa.tp.MongoDBUserRepository;
import insa.tp.Neo4JUserRepository;
import insa.tp.models.MongoDBUser;
import insa.tp.models.Neo4JUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("users")
public class UserController {
    @Autowired
    private MongoDBUserRepository mongoDBUserRepository;
    @Autowired
    private Neo4JUserRepository neo4JUserRepository;

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByLogin(@QueryParam("login") String login){
        Optional<MongoDBUser> opt = mongoDBUserRepository.findByLogin(login);
        if (opt.isEmpty()) return Response.status(Response.Status.NOT_FOUND).build();
        MongoDBUser mongoDBUser = opt.get();
        return Response.ok(mongoDBUser).build();
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(MongoDBUser u){
        Optional<MongoDBUser> opt = mongoDBUserRepository.findByLogin(u.getLogin());
        if(opt.isEmpty()) {
            MongoDBUser mongoDBUser = mongoDBUserRepository.save(u);
            Neo4JUser neo4JUser = new Neo4JUser(mongoDBUser.getLogin());
            neo4JUser = neo4JUserRepository.save(neo4JUser);
            return Response.ok(mongoDBUser).build();
        }
        else return Response.status(Response.Status.UNAUTHORIZED).build();


    }

    @GET
    @Path("get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser(@QueryParam("page") int page){
        List<MongoDBUser> list = mongoDBUserRepository.findAll();
        if(list.isEmpty()) return Response.status(Response.Status.NOT_FOUND).build();
        if (page*30 <= list.size() || (list.size() - 1 <= page*30 -1 && list.size() >= (page-1)*30)){
            List<MongoDBUser> res = new ArrayList<>();
            int index = (page-1)*30;
            while(index < list.size() && index <= page*30){
                res.add(list.get(index));
                index++;
            }
            return Response.ok(res).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("test")
    public String test(){
        return "Je fonctionne!";
    }
}

