package ske.eventsourcing.eventstore;

import java.util.UUID;

public class UUIDEventSourceIdentifier implements EventSourceIdentifier {
    private final UUID uuid;

    public UUIDEventSourceIdentifier() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String asString() {
        return uuid.toString();
    }

}
