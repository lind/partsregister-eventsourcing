package ske.eventsourcing.domain;

import com.google.common.base.Objects;
import ske.eventsourcing.event.EventHandler;
import ske.eventsourcing.eventstore.EventSourceIdentifier;
import ske.eventsourcing.eventstore.StringEventSourceIdentifier;

public class DummyAggregateRoot extends AnnotatedAggregateRoot {

	private String address;
	private int age;
	private String name;

	public DummyAggregateRoot(EventSourceIdentifier id) {
		super(id);
	}

	public DummyAggregateRoot(StringEventSourceIdentifier id, String name) {
		this(id);
		apply(new DummyAggregateCreatedEvent(id, name));
	}

	public void changeAddress(String address) {
		apply(new DummyChangeAddressEvent(getId(), address));
	}

	public void changeAge(int age) {
		apply(new DummyChangedAgeEvent(getId(), age));
	}

	public void changeName(String name) {
		apply(new DummyChangedNameEvent(getId(), name));
	}

	// Event handlers

    @EventHandler
	public void createEvent(DummyAggregateCreatedEvent event) {
		this.name = event.getName();
	}

    @EventHandler
	public void handleChangeAddressEvent(DummyChangeAddressEvent event) {
		this.address = event.getAddress();
	}

    @EventHandler
	public void handleChangedAge(DummyChangedAgeEvent event) {
		this.age = event.getAge();
	}

    @EventHandler
	public void changedNameEvent(DummyChangedNameEvent event) {
		this.name = event.getName();
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("address", address).add("age", age).add("name", name).add("id", getEventSourceIdentifier()).toString();
	}

}
