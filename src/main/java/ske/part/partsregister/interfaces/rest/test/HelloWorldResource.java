package ske.part.partsregister.interfaces.rest.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class HelloWorldResource {

    private final TestService testService;

    @Inject
    public HelloWorldResource(TestService testService) {
        this.testService = testService;
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // localhost:8080/rest/hello
    @GET
    public Message helloWorld() {
        logger.debug("helloWorld");
        return hello("world!");
    }

    @GET
    @Path("/{name}")
    public Message hello(@PathParam("name") String name) {

        return new Message(String.format("Hello, %s!", name) + testService.getTxt());
    }

    @GET
    @Path("/ping")
    public Message ping() {
        logger.debug("ping()");
        return new Message("pong");
    }

    @POST
    @Path("/foo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String fooPost(@QueryParam("bar") String bar) {
        return "The foo " + bar;
    }

}
