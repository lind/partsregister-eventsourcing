package ske.part.partview.interfaces.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.part.partview.interfaces.rest.PartDTO;
import ske.part.partview.infrastructure.PartViewStore;

@Path("/partview")
@Produces(MediaType.APPLICATION_JSON)
public class PartViewResource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PartViewStore viewStore;

    public PartViewResource(PartViewStore viewStore) {
        this.viewStore = viewStore;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public PartDTO hentPart(@QueryParam("partId") String partId) {
        logger.debug("/partview - hentPart() - partId: {}", partId);

        Optional<PartDTO> part = viewStore.hentPart(partId);

        if (!part.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return part.orNull();
    }

    @GET
    @Path("/{id}")
    public PartDTO hentPartMedId(@PathParam("id") String partId) {
        logger.debug("/partview - hentPartMedId() - partId: {}", partId);

        Optional<PartDTO> part = viewStore.hentPart(partId);

        if (!part.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return part.orNull();
    }
}
