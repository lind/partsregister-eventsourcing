package ske.eventsourcing.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import ske.eventsourcing.event.DomainEvent;
import ske.eventsourcing.eventstore.StringEventSourceIdentifier;

public class AggregateRootTest {

    @Test
    public void shouldGetNewName() {
        // Given
        StringEventSourceIdentifier id = new StringEventSourceIdentifier("1");
        DummyAggregateRoot dummyAggregate = new DummyAggregateRoot(id, "stefan");

        // When
        dummyAggregate.changeName("kalle");

        // Then
        assertThat(dummyAggregate.getName(), is("kalle"));
        assertThat(dummyAggregate.getUnsavedEvents().size(), is(2));
    }

    @Test
    public void shouldReloadStateFromEvents() {
        // Given
        StringEventSourceIdentifier id = new StringEventSourceIdentifier("2");
        DummyAggregateRoot dummyAggregate = new DummyAggregateRoot(id, "arne");

        List<DomainEvent> events = new ArrayList<DomainEvent>();
        events.add(new DummyChangedNameEvent(id, "nisse"));
        events.add(new DummyChangedNameEvent(id, "anna"));

        // When
        dummyAggregate.load(events);

        // Then
        assertThat(dummyAggregate.getName(), is("anna"));
    }
}
