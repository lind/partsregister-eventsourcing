package ske.eventsourcing.event;

import java.lang.annotation.*;

/**
 * Annotation for event handling methods in aggregate.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
}
