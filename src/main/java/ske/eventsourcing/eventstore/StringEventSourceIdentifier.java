package ske.eventsourcing.eventstore;

public class StringEventSourceIdentifier implements EventSourceIdentifier {

    private final String identifier;

    public StringEventSourceIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override public String asString() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StringEventSourceIdentifier that = (StringEventSourceIdentifier) o;

        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }
}
