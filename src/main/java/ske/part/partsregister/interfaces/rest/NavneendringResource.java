package ske.part.partsregister.interfaces.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.eventsourcing.eventstore.StringEventSourceIdentifier;
import ske.part.partsregister.application.EndreNavnCommand;
import ske.part.partsregister.application.PartCommandHandler;

@Path("/navneendring")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class NavneendringResource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PartCommandHandler commandHandler;

    @Inject
    public NavneendringResource(PartCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    // Kommando som egen REST ressurs
    @POST
    public void navneendring(NavneendringDTO navneendring) {
        logger.debug("/navneendring - partId: {}", navneendring.getId());

        commandHandler
                .handle(new EndreNavnCommand(new StringEventSourceIdentifier(navneendring.getId()),
                        navneendring.getFornavn(),
                        navneendring.getEtternavn()));
    }

}
