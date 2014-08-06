package ske.eventsourcing.domain;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.eventsourcing.event.Event;
import ske.eventsourcing.eventstore.EventSourceIdentifier;
import ske.eventsourcing.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * Base class for aggregate roots using reflection to find handler methods.
 */
public class SimpleAggregateRoot extends AggregateRoot {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SimpleAggregateRoot(EventSourceIdentifier id) {
        super(id);
    }

    @Override
    protected void handle(Event event) {
        logger.debug("handle event: {}", event);

        // Preconditions.checkNotNull(handlerMethod,
        // "No handle method for event: {}", event.getClass().getSimpleName());
        // handlerMethod.handleEvent(event);

        Method method = ReflectionUtils.findMethod(this.getClass(), "handle",
                event.getClass());
        Preconditions.checkNotNull(method, "No handle method for event: {}",
                event.getClass().getSimpleName());
        // TODO: validate method not null
        ReflectionUtils.invokeMethod(method, this, event);
    }
}
