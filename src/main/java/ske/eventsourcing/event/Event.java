package ske.eventsourcing.event;

import org.joda.time.DateTime;
import ske.eventsourcing.eventstore.EventSourceIdentifier;

public interface Event {

    EventSourceIdentifier getEventSourceIdentifier();

    public DateTime getEventCreated();

    Long getSequenceNumber();

    void setSequenceNumber(long sequenceNumber);
}
