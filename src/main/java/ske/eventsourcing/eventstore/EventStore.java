package ske.eventsourcing.eventstore;

import java.util.List;

import ske.eventsourcing.event.Event;

public interface EventStore {

    <T extends EventSource> T loadEventSource(Class<T> type, EventSourceIdentifier id);

    // <T extends AggregateRoot> T loadEvent(Class<T> type, UUID id);

    // < T extends AggregateRoot> T getByVersionedId(Class<T> type, VersionedId
    // id);

    <T extends EventSource> void save(T eventsource);

    public <T extends EventSource> List<Event> getEventsForIdentifier(Class<T> type, EventSourceIdentifier id);

}
