package ske.part.partsregister.interfaces.rest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

    @Path("/pojo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimplePojo simplePojoJSON(SimplePojo simplePojo) {
        logger.debug("/part/pojo - navn {}, alder {}", simplePojo.getNavn(), simplePojo.getAlder());
        return new SimplePojo("Kalle", 99);
    }


    // ?? {"id":"3", "fornavn":"Nisse", "etternavn":"Danielsson"}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void opprettPart(OpprettPersonCommand command) {
        logger.debug("/part.opprettPart() - partId: {}", command.getId());

        commandHandler.handle(command);
    }

}
