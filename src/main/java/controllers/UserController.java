package controllers;

import dbutils.DBUtilUser;
import models.User;
import services.UserService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;

/**
 * Created by Мадина on 21.11.2014.
 */
@Path("/users")
public class UserController {
    DBUtilUser dbutil = new DBUtilUser();

    @Path("/all")
    @GET
    @Produces("application/json")
    public JsonArray getAll(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (User user : dbutil.findAll()) {
            builder.add(Json.createObjectBuilder().add("email", user.getEmail()));
        }
        return builder.build();
    }
}
