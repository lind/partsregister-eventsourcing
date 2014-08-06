package ske.part.partsregister.domain.part;

import ske.eventsourcing.event.DomainEvent;
import ske.eventsourcing.eventstore.EventSourceIdentifier;

public class EtternavnEndretEvent extends DomainEvent {

    private final String etternavn;

    public EtternavnEndretEvent(EventSourceIdentifier id, String etternavn) {
        super(id);
        this.etternavn = etternavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

}
