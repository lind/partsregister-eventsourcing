package ske.part.partsregister.interfaces.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.eventsourcing.event.Event;
import ske.eventsourcing.eventstore.StringEventSourceIdentifier;
import ske.part.partsregister.application.EndreNavnCommand;
import ske.part.partsregister.application.OpprettPersonCommand;
import ske.part.partsregister.application.PartCommandHandler;
import ske.part.partsregister.domain.part.Part;

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

    // -----------------------------------------------------------------------------------------------------
    // API
    // -----------------------------------------------------------------------------------------------------
    // ?? {"id":"3", "fornavn":"Nisse", "etternavn":"Danielsson"}
    @POST
    public void opprettPart(OpprettPersonDTO opprettPerson) {
        logger.debug("/part.opprettPart() - partId: {}", opprettPerson.getId());

        commandHandler.handle(new OpprettPersonCommand(opprettPerson.getId(), opprettPerson.getFornavn(),
                opprettPerson.getEtternavn()));
    }

    @Path("/{id}/endre_navn")
    @POST
    public void endreNavn(@PathParam("id") String id, EndreNavnDTO endreNavn) {
        logger.debug("/part/{id}/endre_navn - partId: {}", id);

        commandHandler
                .handle(new EndreNavnCommand(new StringEventSourceIdentifier(id), endreNavn.getFornavn(),
                        endreNavn.getEtternavn()));
    }

    @Path("/{id}")
    @GET
    public PartDTO hentPart(@PathParam("id") String id) {
        Part part = commandHandler.getPart(id);
        return new PartDTO(part.getId().asString(), part.getFornavn(), part.getEtternavn());
    }

    @Path("/{id}/events")
    @GET
    public String hentEventer(@PathParam("id") String id) {
        List<Event> events = commandHandler.getEvents(id);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(events);
    }

    // -----------------------------------------------------------------------------------------------------
    // Test
    // -----------------------------------------------------------------------------------------------------

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

}
