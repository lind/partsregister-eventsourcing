package ske.eventsourcing.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.eventsourcing.event.DomainEvent;
import ske.eventsourcing.event.Event;
import ske.eventsourcing.eventstore.EventSource;
import ske.eventsourcing.eventstore.EventSourceIdentifier;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot implements EventSource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final EventSourceIdentifier id;

    // TODO: version (Long) as sequence
    private final List<DomainEvent> unsavedEvents = new ArrayList<DomainEvent>();

    public AggregateRoot(EventSourceIdentifier id) {
        this.id = id;
    }

    protected void apply(DomainEvent event) {
        log.debug("apply() - event: {} - to Aggregate: {} ", event, this.getClass().getSimpleName());
        unsavedEvents.add(event);
        handle(event);
    }

    public EventSourceIdentifier getId() {
        return id;
    }

    @Override
    public void load(Iterable<? extends Event> events) {
        for (Event event : events) {
            handle(event);
        }
    }

    @Override
    public List<Event> getUnsavedEvents() {
        return new ArrayList<Event>(unsavedEvents);
    }

    @Override
    public EventSourceIdentifier getEventSourceIdentifier() {
        return id;
    }

    @Override
    public void clearUnsavedEvents() {
        unsavedEvents.clear();
    }

    protected abstract void handle(Event event);

}
