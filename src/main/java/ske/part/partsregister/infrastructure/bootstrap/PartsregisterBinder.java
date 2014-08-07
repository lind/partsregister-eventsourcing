package ske.part.partsregister.infrastructure.bootstrap;

import com.google.common.eventbus.EventBus;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ske.eventsourcing.eventstore.EventStore;
import ske.eventsourcing.eventstore.InMemoryEventStore;
import ske.part.partsregister.application.PartCommandHandler;
import ske.part.partview.infrastructure.PartEventHandler;
import ske.part.partview.infrastructure.PartViewStore;

public class PartsregisterBinder extends AbstractBinder {

    @Override
    protected void configure() {

        // Gives single instances of the EventStore and EventBus
        EventBus eventBus = new EventBus();
        EventStore eventStore = new InMemoryEventStore();

        bind(PartCommandHandler.class).to(PartCommandHandler.class);
        bind(eventStore).to(EventStore.class);
        bind(eventBus).to(EventBus.class);

        PartViewStore partViewStore = new PartViewStore();
        PartEventHandler partEventHandler = new PartEventHandler(partViewStore);
        eventBus.register(partEventHandler);
        bind(partViewStore).to(PartViewStore.class);

    }

}
