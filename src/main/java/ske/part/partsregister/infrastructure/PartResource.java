package ske.part.partsregister.infrastructure;

import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.part.partsregister.application.OpprettPersonCommand;
import ske.part.partsregister.application.PartCommandHandler;

@Path("/part")
@Consumes(MediaType.APPLICATION_JSON)
public class PartResource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PartCommandHandler commandHandler;

    public PartResource(PartCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        // String template, String defaultName
        //            this.template = template;
        //            this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @Path("/test")
    @POST
    @Timed
    public void test(String id) {
        logger.debug("/part/test.test() - partId: {}", id);

    }

    @POST
    @Timed
    public void opprettPart(Optional<OpprettPersonCommand> command) {
        logger.debug("/part.opprettPart() - partId: {}",
                command.isPresent() ? command.get().getId() : "-- Command=null --");

        commandHandler.handle(command.orNull());
    }

}
