package ske.part.partsregister.interfaces.rest;

import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.part.partsregister.application.OpprettPersonCommand;
import ske.part.partsregister.application.PartCommandHandler;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/part")
@Consumes(MediaType.APPLICATION_JSON)
public class PartResource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private PartCommandHandler commandHandler;

    public PartResource(PartCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Path("/test")
    @POST
//    @Timed  // metrics
    public void test(String id) {
        System.out.println("/part/test.test() - partId:");
        logger.debug("/part/test.test() - partId: {}", id);

    }

    @POST
//    @Timed
    public void opprettPart(Optional<OpprettPersonCommand> command) {
        logger.debug("/part.opprettPart() - partId: {}",
                command.isPresent() ? command.get().getId() : "-- Command=null --");
        System.out.println("/part.opprettPart() - partId:");

        commandHandler.handle(command.orNull());
    }

}
