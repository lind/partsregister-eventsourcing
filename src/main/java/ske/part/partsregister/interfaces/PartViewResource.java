package ske.part.partsregister.interfaces;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/partview")
@Produces(MediaType.APPLICATION_JSON)
public class PartViewResource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PartViewStore viewStore;

    public PartViewResource(PartViewStore viewStore) {
        this.viewStore = viewStore;
    }

    @GET
    @Timed
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
    @Timed
    public PartDTO hentPartMedId(@PathParam("id") String partId) {
        logger.debug("/partview - hentPartMedId() - partId: {}", partId);

        Optional<PartDTO> part = viewStore.hentPart(partId);

        if (!part.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return part.orNull();
    }
}
