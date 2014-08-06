package ske.eventsourcing.domain;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.eventsourcing.event.Event;
import ske.eventsourcing.event.EventHandler;
import ske.eventsourcing.eventstore.EventSourceIdentifier;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Base class for aggregate roots with annotated handler methods.
 */
public class AnnotatedAggregateRoot extends AggregateRoot {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Map<Class<?>, EventHandlerMethod> eventHandlerMethodMap;

    public AnnotatedAggregateRoot(EventSourceIdentifier id) {
        super(id);
        eventHandlerMethodMap = findAllEventHandlers(this);
    }

    protected void handle(Event event) {
        log.debug("handle event: {}", event);
        EventHandlerMethod handlerMethod = eventHandlerMethodMap.get(event.getClass());

        Preconditions.checkNotNull(handlerMethod, "Class %s has no handle method for event: %s", this.getClass().getSimpleName(), event.getClass().getSimpleName());
        handlerMethod.handleEvent(event);
    }

    private Map<Class<?>, EventHandlerMethod> findAllEventHandlers(Object source) {
        Map<Class<?>, EventHandlerMethod> eventHandlers = Maps.newHashMap();
        Class<? extends Object> clazz = source.getClass();

        for (Method method : clazz.getMethods()) {
            EventHandler annotation = method.getAnnotation(EventHandler.class);

            if (null != annotation) {
                Class<?>[] parameterTypes = method.getParameterTypes();

                if (parameterTypes.length != 1) {
                    throw new IllegalArgumentException("Method " + method
                            + "has @Eventhandler annotation and requires " + parameterTypes.length
                            + " number of arguments. Only one argument is allowed.");
                }

                Class<?> eventType = parameterTypes[0];
                if (eventHandlers.containsKey(eventType)) {
                    throw new IllegalStateException("Class " + source.getClass()
                            + " has more than one method annotated with @EventHandler with parametertype " + eventType
                            + "!");
                }

                // TODO: Force?
                method.setAccessible(true);
                eventHandlers.put(eventType, new EventHandlerMethod(source, method));
            }
        }
        return eventHandlers;
    }

}
