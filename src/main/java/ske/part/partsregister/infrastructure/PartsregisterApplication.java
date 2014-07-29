package ske.part.partsregister.infrastructure;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PartsregisterApplication extends Application<PartsregisterConfiguration> {

    public static void main(String[] args) throws Exception {
        new PartsregisterApplication().run(args);
    }

    @Override
    public String getName() {
        return "Partsregister";
    }

    @Override
    public void initialize(Bootstrap<PartsregisterConfiguration> bootstrap) {

    }

    @Override
    public void run(PartsregisterConfiguration configuration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
