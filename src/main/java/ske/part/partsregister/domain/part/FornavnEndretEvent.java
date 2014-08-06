package ske.part.partsregister.domain.part;

import ske.eventsourcing.event.DomainEvent;
import ske.eventsourcing.eventstore.EventSourceIdentifier;

public class FornavnEndretEvent extends DomainEvent {

    private final String fornavn;

    public FornavnEndretEvent(EventSourceIdentifier id, String fornavn) {
        super(id);
        this.fornavn = fornavn;
    }

    public String getFornavn() {
        return fornavn;
    }

}
