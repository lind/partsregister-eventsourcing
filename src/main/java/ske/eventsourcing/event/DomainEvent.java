package ske.eventsourcing.event;

import com.google.common.base.Objects;
import ske.eventsourcing.eventstore.EventSourceIdentifier;

public class DomainEvent implements Event {

    //    private final DateTime eventCreated;
    private EventSourceIdentifier id;
    private long sequenceNumber;

    public DomainEvent(EventSourceIdentifier id) {
        this.id = id;
        //        this.eventCreated = new DateTime();
    }

    //    @Override
    //    public DateTime getEventCreated() {
    //        return eventCreated;
    //    }

    public EventSourceIdentifier getEventSourceIdentifier() {
        return id;
    }

    public void setEventSourceIdentifier(EventSourceIdentifier id) {
        this.id = id;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public void setSequenceNumber(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("id", id != null ? id.asString() : "null").toString();
    }
}
