package ske.part.partsregister.application;

import ske.eventsourcing.eventstore.EventSourceIdentifier;
import ske.eventsourcing.eventstore.StringEventSourceIdentifier;

public class OpprettPersonCommand {
    private EventSourceIdentifier id;
    private String fornavn;
    private String etternavn;

    public OpprettPersonCommand() {
    }

    public OpprettPersonCommand(String id, String fornavn, String etternavn) {
        this.id = new StringEventSourceIdentifier(id);
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public OpprettPersonCommand(EventSourceIdentifier id, String fornavn, String etternavn) {
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
