package ske.part.partview.interfaces.rest;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.part.partview.domain.PartViewModel;
import ske.part.partview.infrastructure.PartViewStore;

@Path("/partview")
@Produces(MediaType.APPLICATION_JSON)
public class PartViewResource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PartViewStore viewStore;

    @Inject
    public PartViewResource(PartViewStore viewStore) {
        this.viewStore = viewStore;
    }

    @GET
    @Path("/{id}")
    public PartViewDTO hentPart(@PathParam("id") String id) {
        logger.debug("/partview/{id} - id: {}", id);

        PartViewModel part = viewStore.hentPart(id);

        if (null == part) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new PartViewDTO(part.getId(), part.getEtternavn());
    }
}
