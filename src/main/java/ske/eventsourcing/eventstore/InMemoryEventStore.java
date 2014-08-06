package ske.eventsourcing.eventstore;

import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.eventsourcing.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * InMemory storage of events. <b> Events are stored in a List in a Map where the type of eventsource and the id are the
 * key.
 */
public class InMemoryEventStore extends BaseEventStore {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<EventSourceKey, List<Event>> eventStorage = newHashMap();

    public <T extends EventSource> T loadEventSource(Class<T> type, EventSourceIdentifier id) {
        EventSourceKey key = new EventSourceKey(type, id);

        logger.debug("Loading events with key:{}", key);

        List<Event> events = eventStorage.get(key);
        logger.debug("Nr of events from source with type:{} and id:{} are:{}", type, id,
                (null == events ? 0 : events.size()));

        T eventSource = createEventSource(type, id);

        if (null != events) {
            eventSource.load(events);
        }

        return eventSource;
    }

    public <T extends EventSource> void save(T eventsource) {

        EventSourceKey key = new EventSourceKey(eventsource.getClass(), eventsource.getEventSourceIdentifier());
        List<Event> events = eventStorage.get(key);

        if (null == events) {
            events = new ArrayList<>();
        }

        int numberOfStoredEvents = events.size();
        List<Event> eventsToSave = eventsource.getUnsavedEvents();
        logger.debug("save() - number of events on {} before save: {}", eventsource.getEventSourceIdentifier().asString(),
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
        public int hashCode() {

            return Objects.hashCode(id, type);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof EventSourceKey))
                return false;
            EventSourceKey other = (EventSourceKey) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.asString().equals(other.id.asString()))
                return false;
            if (type == null) {
                if (other.type != null)
                    return false;
            } else if (!type.equals(other.type))
                return false;
            return true;
        }

        private InMemoryEventStore getOuterType() {
            return InMemoryEventStore.this;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("type", type.getSimpleName()).add("id", id.asString()).toString();
        }
    }
}
