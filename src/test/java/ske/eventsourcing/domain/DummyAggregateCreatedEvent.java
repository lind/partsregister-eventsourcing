package ske.eventsourcing.domain;

import ske.eventsourcing.event.DomainEvent;
import ske.eventsourcing.eventstore.StringEventSourceIdentifier;

public class DummyAggregateCreatedEvent extends DomainEvent {

	private final String name;

	public DummyAggregateCreatedEvent(StringEventSourceIdentifier id, String name) {
        super(id);
        this.name = name;
	}

	public String getName() {
		return name;
	}

}
