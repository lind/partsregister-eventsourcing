package ske.part.partsregister.application;

import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.eventsourcing.event.Event;
import ske.eventsourcing.eventstore.EventSourceIdentifier;
import ske.eventsourcing.eventstore.EventStore;
import ske.part.partsregister.domain.part.Part;

import javax.inject.Inject;
import java.util.List;

public class PartCommandHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private EventBus eventBus;
    private EventStore eventStore;

    @Inject
    public PartCommandHandler(EventStore eventStore, EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventStore = eventStore;
    }

    public EventSourceIdentifier handle(OpprettPersonCommand command) {
        logger.debug("handle(OpprettPersonCommand) - Fornavn: {} ", command.getFornavn());

        // TODO: Validate if part exists

        Part part = new Part(command.getId(), command.getFornavn(), command.getEtternavn());

        if (null != eventBus) {
            List<Event> events = part.getUnsavedEvents();
            for (Event event : events) {
                eventBus.post(event);
            }
        }
        eventStore.save(part);
        return part.getEventSourceIdentifier();
    }

    public void handle(EndreNavnCommand endreNavnCommand) {
        logger.debug("handle(EndreNavnCommand) - Fornavn: {}", endreNavnCommand.getFornavn());

        Part part = eventStore.loadEventSource(Part.class, endreNavnCommand.getId());

        part.endreFornavn(endreNavnCommand.getFornavn());
        part.endreEtternavn(endreNavnCommand.getEtternavn());

        if (null != eventBus) {
            List<Event> events = part.getUnsavedEvents();
            for (Event event : events) {
                eventBus.post(event);
            }
        }

        eventStore.save(part);
    }
}
