package ske.part.partsregister.domain.part;

import ske.eventsourcing.event.DomainEvent;
import ske.eventsourcing.eventstore.EventSourceIdentifier;

public class PersonOpprettetEvent extends DomainEvent {

    private final String fornavn;
    private final String etternavn;

    public PersonOpprettetEvent(EventSourceIdentifier id, String fornavn, String etternavn) {
        super(id);
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

}
