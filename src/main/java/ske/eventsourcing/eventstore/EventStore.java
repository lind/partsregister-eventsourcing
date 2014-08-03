package ske.eventsourcing.eventstore;

import ske.eventsourcing.event.Event;

import java.util.List;

public interface EventStore {

    <T extends EventSource> T loadEventSource(Class<T> type, EventSourceIdentifier id);

    // <T extends AggregateRoot> T loadEvent(Class<T> type, UUID id);

    // < T extends AggregateRoot> T getByVersionedId(Class<T> type, VersionedId
    // id);

    <T extends EventSource> void save(T eventsource);

    public <T extends EventSource> List<Event> getEventsForIdentifier(Class<T> type, EventSourceIdentifier id);

}
