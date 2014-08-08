package ske.part.partsregister.infrastructure.bootstrap;

import org.glassfish.jersey.server.ResourceConfig;

public class PartsregisterApplication extends ResourceConfig {
    public PartsregisterApplication() {
        register(new PartsregisterBinder());
        // ske.part.partview.interfaces.rest skal være en egen applikasjon men for test så ligger den her...
        packages(true, "ske.part.partsregister.interfaces.rest", "ske.part.partview.interfaces.rest");
    }
}
