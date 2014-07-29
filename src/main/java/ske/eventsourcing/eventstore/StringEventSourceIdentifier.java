package ske.eventsourcing.eventstore;

public class StringEventSourceIdentifier implements EventSourceIdentifier {

    private final String identifier;

    public StringEventSourceIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override public String asString() {
        return identifier;
    }
}
