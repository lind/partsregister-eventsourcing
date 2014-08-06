package ske.eventsourcing.domain;

import ske.eventsourcing.event.DomainEvent;
import ske.eventsourcing.eventstore.EventSourceIdentifier;

public class DummyChangedNameEvent extends DomainEvent {

    private final String name;

    public DummyChangedNameEvent(EventSourceIdentifier id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
