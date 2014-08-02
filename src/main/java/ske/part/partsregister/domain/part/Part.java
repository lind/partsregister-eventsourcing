package ske.part.partsregister.domain.part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.eventsourcing.domain.AnnotatedAggregateRoot;
import ske.eventsourcing.event.EventHandler;
import ske.eventsourcing.eventstore.EventSourceIdentifier;

public class Part extends AnnotatedAggregateRoot {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String fornavn;
    // private String mellomnavn;
    private String etternavn;

    public Part(EventSourceIdentifier id) {
        super(id);
    }

    public Part(EventSourceIdentifier id, String fornavn, String etternavn) {
        super(id); // new UUIDEventSourceIdentifier());
        apply(new PersonOpprettetEvent(getEventSourceIdentifier(), fornavn, etternavn));
    }

    public void endreFornavn(String fornavn) {
        apply(new FornavnEndretEvent(getEventSourceIdentifier(), fornavn));
    }

    public void endreEtternavn(String etternavn) {
        apply(new EtternavnEndretEvent(getEventSourceIdentifier(), etternavn));
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    @EventHandler
    public void handle(PersonOpprettetEvent event) {
        log.debug("handle() -> PersonOpprettetEvent()");
        this.fornavn = event.getFornavn();
        this.etternavn = event.getEtternavn();
    }

    @EventHandler
    public void handle(FornavnEndretEvent event) {
        log.debug("handle() -> FornavnEndretEvent");
        this.fornavn = event.getFornavn();
    }

    @EventHandler
    public void handle(EtternavnEndretEvent event) {
        log.debug("handle() -> EtternavnEndretEvent");
        this.etternavn = event.getEtternavn();
    }

}
