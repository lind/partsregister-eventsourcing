package ske.eventsourcing.domain;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import ske.eventsourcing.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * Container for event-method pairs in a annotated aggregate root.
 */
public class EventHandlerMethod {

    private final Object target;
    private final Method method;

    public EventHandlerMethod(Object target, Method method) {
        Preconditions.checkNotNull(target, "HandlerMethod target cannot be null.");
        Preconditions.checkNotNull(method, "HandlerMethod method cannot be null.");
        this.target = target;
        this.method = method;
    }

    public void handleEvent(Object event) {
        ReflectionUtils.invokeMethod(method, target, new Object[]{event});
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(method, target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof EventHandlerMethod)) {
            return false;
        }
        EventHandlerMethod other = (EventHandlerMethod) obj;
        if (method == null) {
            if (other.method != null) {
                return false;
            }
        } else if (!method.equals(other.method)) {
            return false;
        }
        if (target == null) {
            if (other.target != null) {
                return false;
            }
        } else if (!target.equals(other.target)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        // return Objects.toStringHelper(this).add("target",
        // target).add("method", method).toString();
        return "HandlerMethod [target=" + target + ", method=" + method + "]";
    }

}
