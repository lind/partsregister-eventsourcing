package ske.eventsourcing.domain;

import com.google.common.base.Objects;
import ske.eventsourcing.event.DomainEvent;
import ske.eventsourcing.eventstore.EventSourceIdentifier;

public class DummyChangedAgeEvent extends DomainEvent {

    public final int age;

    public DummyChangedAgeEvent(EventSourceIdentifier id, int age) {
        super(id);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("age", age).toString();
    }

}
