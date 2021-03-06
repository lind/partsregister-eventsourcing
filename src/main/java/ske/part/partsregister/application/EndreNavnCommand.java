package ske.part.partsregister.application;

import ske.eventsourcing.eventstore.EventSourceIdentifier;

public class EndreNavnCommand {
    private EventSourceIdentifier id;
    private String fornavn;
    private String etternavn;

    public EndreNavnCommand(EventSourceIdentifier id, String fornavn, String etternavn) {
        this.id = id;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public EventSourceIdentifier getId() {
        return id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

}
