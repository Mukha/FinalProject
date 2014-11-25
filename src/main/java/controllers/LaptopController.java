package controllers;

/**
 * Created by Мадина on 22.11.2014.
 */

import dbutils.DBUtilLaptop;
import models.Laptop;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/laptops")
public class LaptopController {

    DBUtilLaptop dbUtilLaptop = new DBUtilLaptop();

    @GET
    @Path("/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Laptop> findAll() {
        System.out.println("findAll");
        return dbUtilLaptop.findAll();
    }

    @GET
    @Path("search/{query}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Laptop> findByName(@PathParam("query") String query) {
        System.out.println("findByName: " + query);
        return dbUtilLaptop.findByName(query);
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Laptop findById(@PathParam("id") String id) {
        System.out.println("findById " + id);
        return dbUtilLaptop.findById(Integer.parseInt(id));
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Laptop create(Laptop laptop) {
        System.out.println("creating laptop");
        return dbUtilLaptop.create(laptop);
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Laptop update(Laptop laptop) {
        System.out.println("Updating laptop is : " + laptop.getBrandName());
        dbUtilLaptop.update(laptop);
        return laptop;
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void remove(@PathParam("id") int id) {
        dbUtilLaptop.remove(id);
    }

}

