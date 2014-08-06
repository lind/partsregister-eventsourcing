package ske.part.partsregister.infrastructure.bootstrap;

import com.google.common.eventbus.EventBus;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ske.eventsourcing.eventstore.EventStore;
import ske.eventsourcing.eventstore.InMemoryEventStore;
import ske.part.partsregister.application.PartCommandHandler;
import ske.part.partsregister.interfaces.rest.test.TestService;
import ske.part.partview.infrastructure.PartEventHandler;
import ske.part.partview.infrastructure.PartViewStore;

public class PartsregisterBinder extends AbstractBinder {

    @Override
    protected void configure() {

        EventBus eventBus = new EventBus();

        bind(PartCommandHandler.class).to(PartCommandHandler.class);
        bind(InMemoryEventStore.class).to(EventStore.class);
        bind(eventBus).to(EventBus.class);


        PartViewStore partViewStore = new PartViewStore();
        PartEventHandler partEventHandler = new PartEventHandler(partViewStore);
        eventBus.register(partEventHandler);

//        bind(TestService.class).to(TestService.class);


    }

}
