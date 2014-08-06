package ske.part.partsregister.interfaces.rest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.part.partsregister.application.OpprettPersonCommand;
import ske.part.partsregister.application.PartCommandHandler;

@Path("/parter")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
public class PartResource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PartCommandHandler commandHandler;

    @Inject
    public PartResource(PartCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Path("/hei")
    @GET
    public String hei() {
        return "hei";
    }

    @Path("/test")
    @POST
    public void test(String id) {
        logger.debug("/part/test.test() - partId: {}", id);

    }

    // ?? {"id":"3", "fornavn":"Nisse", "etternavn":"Danielsson"}

    @POST
    public void opprettPart(Optional<OpprettPersonCommand> command) {
        System.out.println("/part.opprettPart() - partId:");
        logger.debug("/part.opprettPart() - partId: {}",
                command.isPresent() ? command.get().getId() : "-- Command=null --");
        System.out.println("/part.opprettPart() - partId:");

        commandHandler.handle(command.orNull());
    }

}
