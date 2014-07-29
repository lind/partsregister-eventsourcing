package ske.eventsourcing.domain;

import java.lang.reflect.Method;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.eventsourcing.event.Event;
import ske.eventsourcing.eventstore.EventSourceIdentifier;
import ske.eventsourcing.util.ReflectionUtils;

public class SimpleAggregateRoot extends AggregateRoot {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SimpleAggregateRoot(EventSourceIdentifier id) {
        super(id);
    }

    @Override
    protected void handle(Event event) {
        log.debug("handle event: {}", event);

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
