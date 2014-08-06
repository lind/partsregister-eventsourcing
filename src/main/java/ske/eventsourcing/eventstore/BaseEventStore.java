package ske.eventsourcing.eventstore;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class BaseEventStore implements EventStore {

    /**
     * Instantiates a event source with given identifier.
     *
     * @param type EventSource type
     * @param id   identifier
     * @param <T>
     * @return instance of the event source
     */
    protected <T> T createEventSource(Class<T> type, EventSourceIdentifier id) {
        T createdgAgregateRoot;
        try {
            Constructor<T> constructor;
            constructor = type.getConstructor(EventSourceIdentifier.class);
            createdgAgregateRoot = constructor.newInstance(id);

        } catch (SecurityException e) {
            throw new IllegalStateException("Could not access constructor: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Missing constructor: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Constructor argument invalid: " + e.getMessage());
        } catch (InstantiationException e) {
            throw new IllegalStateException("Construction failed: " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not access constructor: " + e.getMessage());
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("Construction failed: " + e.getMessage());
        }
        return createdgAgregateRoot;
    }
}
