package ske.part.partsregister.interfaces.rest.test;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
    public MyApplication() {
        register(new MyApplicationBinder());
        packages(true, "ske.part.partsregister.interfaces.rest");
    }
}