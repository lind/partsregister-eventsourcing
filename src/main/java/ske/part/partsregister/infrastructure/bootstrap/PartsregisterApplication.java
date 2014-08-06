package ske.part.partsregister.infrastructure.bootstrap;

import org.glassfish.jersey.server.ResourceConfig;

public class PartsregisterApplication extends ResourceConfig {
    public PartsregisterApplication() {
        register(new PartsregisterBinder());
        packages(true, "ske.part.partsregister.interfaces.rest");

        // Hvordan sett opp og registrere handlere?
//        partViewStore = new PartViewStore();
//        partEventHandler = new PartEventHandler(partViewStore);
//        eventBus.register(partEventHandler);

    }
}
