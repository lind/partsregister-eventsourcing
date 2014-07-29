package ske.part.partsregister.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.eventsourcing.eventstore.EventSourceIdentifier;
import ske.eventsourcing.eventstore.EventStore;
import ske.part.partsregister.domain.part.Part;

public class PartCommandHandler {

//    @Inject
    public PartCommandHandler(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    private EventStore eventStore;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public EventSourceIdentifier handle(OpprettPersonCommand command) {
        logger.debug("handle(OpprettPersonCommand) - Fornavn:" + command.getFornavn());

        Part part = new Part(command.getId(), command.getFornavn(), command.getEtternavn());
        eventStore.save(part);
        return part.getEventSourceIdentifier();
    }

    public void handle(EndreNavnCommand endreNavnCommand) {
        logger.debug("handle(EndreNavnCommand) - Fornavn:" + endreNavnCommand.getFornavn());

        Part part = eventStore.loadEventSource(Part.class, endreNavnCommand.getId());

        part.endreFornavn(endreNavnCommand.getFornavn());
        part.endreEtternavn(endreNavnCommand.getEtternavn());
        eventStore.save(part);

    }
}
