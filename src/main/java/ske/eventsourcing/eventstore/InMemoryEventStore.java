package ske.eventsourcing.eventstore;

import static com.google.common.collect.Maps.newHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Scope;
import javax.inject.Singleton;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.eventsourcing.event.Event;

/**
 * InMemory storage of events. <b> Events are stored in a List in a Map where the type of eventsource and the id are the
 * key.
 */
public class InMemoryEventStore extends BaseEventStore {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<EventSourceKey, List<Event>> eventStorage = newHashMap();

    public <T extends EventSource> T loadEventSource(Class<T> type, EventSourceIdentifier id) {
        logger.debug("loadEventSource - obj id: {}", this.toString());
        EventSourceKey key = new EventSourceKey(type, id);

        logger.debug("Loading events with key:{}", key);

        List<Event> events = eventStorage.get(key);
        logger.debug("Nr of events from source with type:{} and id:{} are:{}", type.getSimpleName(), id.asString(),
                (null == events ? 0 : events.size()));

        Preconditions.checkState(null != events && events.size() != 0,
                "Storage can't be empty when loading EventSource. Must create and store the EventSource first. Type: " + type + ", id: " + id.asString());

        T eventSource = createEventSource(type, id);

        if (null != events) {
            eventSource.load(events);
        }

        return eventSource;
    }

    public <T extends EventSource> void save(T eventsource) {
        logger.debug("save - obj id: {}", this.toString());

        EventSourceKey key = new EventSourceKey(eventsource.getClass(), eventsource.getEventSourceIdentifier());
        List<Event> events = eventStorage.get(key);

        if (null == events) {
            events = new ArrayList<>();
        }

        int numberOfStoredEvents = events.size();
        List<Event> eventsToSave = eventsource.getUnsavedEvents();
        logger.debug("save() - number of events on {} before save: {}",
                eventsource.getEventSourceIdentifier().asString(),
                numberOfStoredEvents);
        for (Event event : eventsToSave) {
            event.setSequenceNumber(++numberOfStoredEvents);
            events.add(event);
        }
        logger.debug("save() - Storing {} events to key:{}", eventsToSave.size(), key);
        eventStorage.put(key, events);
    }

    // For testing purpose
    public <T extends EventSource> int eventCount(Class<T> type, EventSourceIdentifier id) {
        return eventStorage.get(new EventSourceKey(type, id)).size();
    }

    public <T extends EventSource> List<Event> getEventsForIdentifier(Class<T> type, EventSourceIdentifier id) {
        return eventStorage.get(new EventSourceKey(type, id));
    }

    @Override public <T extends EventSource> boolean exists(Class<T> type, EventSourceIdentifier id) {
        return eventStorage.containsKey(new EventSourceKey(type, id));
    }

    /**
     * Key class for storing events.
     */
    private class EventSourceKey {
        final Class<? extends EventSource> type;
        final EventSourceIdentifier id;

        public EventSourceKey(Class<? extends EventSource> type, EventSourceIdentifier id) {
            this.type = type;
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            EventSourceKey that = (EventSourceKey) o;

            if (!id.equals(that.id)) {
                return false;
            }
            if (!type.equals(that.type)) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = type.hashCode();
            result = 31 * result + id.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("type", type.getSimpleName()).add("id", id.asString()).toString();
        }

    }

}
