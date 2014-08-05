package ske.part.partsregister.infrastructure.bootstrap;

import com.google.common.eventbus.EventBus;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ske.eventsourcing.eventstore.EventStore;
import ske.eventsourcing.eventstore.InMemoryEventStore;
import ske.part.partsregister.application.PartCommandHandler;

public class PartsregisterBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(PartCommandHandler.class).to(PartCommandHandler.class);
        bind(InMemoryEventStore.class).to(EventStore.class);
        bind(EventBus.class).to(EventBus.class);
    }

}
