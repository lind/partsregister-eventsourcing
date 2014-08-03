package ske.eventsourcing.eventstore;

import ske.eventsourcing.event.Event;

import java.util.List;

/**
 * Kilde til event (hendelser). Hver event source har sin egen rekke med event som er lagret i event store. Eventene
 * brukes til å bygge opp tilstanden på event sourcen. I DDD er aggregater event sources.
 */
public interface EventSource {

    /**
     * Bygger opp staten utefra eventene til event sourcen.
     *
     * @param events alle event
     */
    void load(Iterable<? extends Event> events);

    List<Event> getUnsavedEvents();

    // TODO: interface for id to use UUID and other identifiers.
    EventSourceIdentifier getEventSourceIdentifier();

    void clearUnsavedEvents();

}
