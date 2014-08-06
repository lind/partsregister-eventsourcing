package ske.eventsourcing.domain;

import ske.eventsourcing.event.DomainEvent;
import ske.eventsourcing.eventstore.EventSourceIdentifier;

public class DummyChangeAddressEvent extends DomainEvent {

    private final String address;

    public DummyChangeAddressEvent(EventSourceIdentifier id, String address) {
        super(id);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "DummyChangeAddressEvent [address=" + address + ", getEventCreated()=" + getEventCreated().toString()
                + "]";
    }

}
