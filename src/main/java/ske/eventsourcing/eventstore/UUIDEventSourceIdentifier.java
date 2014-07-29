package ske.eventsourcing.eventstore;

import java.util.UUID;

public class UUIDEventSourceIdentifier implements EventSourceIdentifier {
    private final UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public UUIDEventSourceIdentifier() {
        this.uuid = UUID.randomUUID();
    }

    @Override
    public String asString() {
        return uuid.toString();
    }

}
