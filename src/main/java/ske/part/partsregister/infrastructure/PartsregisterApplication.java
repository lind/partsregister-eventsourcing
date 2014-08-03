package ske.part.partsregister.infrastructure;

//import io.dropwizard.Application;
//import io.dropwizard.setup.Bootstrap;
//import io.dropwizard.setup.Environment;

// Brukes med Dropwizard
/*
public class PartsregisterApplication extends Application<PartsregisterConfiguration> {

    private EventBus eventBus;
    private EventStore eventStore;

    // The view store (db) and the event listeners should be in an separate JVM for better performance.
    private PartViewStore partViewStore;
    private PartEventHandler partEventHandler;

    public static void main(String[] args) throws Exception {
        new PartsregisterApplication().run(args);
    }

    @Override
    public String getName() {
        return "Partsregister";
    }

    @Override
    public void initialize(Bootstrap<PartsregisterConfiguration> bootstrap) {

        eventBus = new EventBus("PartEvents");
        eventStore = new InMemoryEventStore();

        partViewStore = new PartViewStore();
        partEventHandler = new PartEventHandler(partViewStore);

        eventBus.register(partEventHandler);
    }

    @Override
    public void run(PartsregisterConfiguration configuration, Environment environment) throws Exception {

        PartCommandHandler commandHandler = new PartCommandHandler(eventStore, Optional.of(eventBus));

        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final PartResource partResource = new PartResource(commandHandler);
        final PartViewResource partViewResource = new PartViewResource(partViewStore);
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(partResource);
        environment.jersey().register(partViewResource);
    }

}

*/