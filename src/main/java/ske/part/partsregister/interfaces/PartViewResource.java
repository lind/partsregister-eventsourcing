package ske.part.partsregister.interfaces;

import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.part.partsregister.application.PartCommandHandler;
import ske.part.partsregister.infrastructure.PartDTO;

@Path("/part")
@Produces(MediaType.APPLICATION_JSON)
public class PartViewResource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

//        private final String template;
//        private final String defaultName;
        private final AtomicLong counter;
    private PartCommandHandler commandHandler;

        public PartViewResource(PartCommandHandler commandHandler) {
            this.commandHandler = commandHandler;
            // String template, String defaultName
//            this.template = template;
//            this.defaultName = defaultName;
            this.counter = new AtomicLong();
        }

        @GET
        @Timed
        public PartDTO hentPart(@QueryParam("partId") String partId) {
            logger.debug("/part.hentPart() - partId: {}", partId);

//            final String value = String.format(template, partId);
            return new PartDTO("testFornavn", "testEtternavn");
        }
}
