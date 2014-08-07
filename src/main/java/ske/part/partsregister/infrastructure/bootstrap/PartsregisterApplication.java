package ske.part.partsregister.infrastructure.bootstrap;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class PartsregisterApplication extends ResourceConfig {
    public PartsregisterApplication() {
        register(new PartsregisterBinder());
        // ske.part.partview.interfaces.rest skal være en egen applikasjon men for test så ligger den her...
        packages(true, new String[] {"ske.part.partsregister.interfaces.rest", "ske.part.partview.interfaces.rest"} );



//        register(MoxyJsonFeature.class); // trenges ikke... Hvorfor??


//        registerInstances(new JsonMoxyConfigurationContextResolver());


        // Hvordan sett opp og registrere handlere?
//        partViewStore = new PartViewStore();
//        partEventHandler = new PartEventHandler(partViewStore);
//        eventBus.register(partEventHandler);

    }

    /** Trenges ikke
    @Provider
    final static class JsonMoxyConfigurationContextResolver implements ContextResolver<MoxyJsonConfig> {

        @Override
        public MoxyJsonConfig getContext(Class<?> objectType) {
            final MoxyJsonConfig configuration = new MoxyJsonConfig();

            Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
            namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");

            configuration.setNamespacePrefixMapper(namespacePrefixMapper);
            configuration.setNamespaceSeparator(':');

            return configuration;
        }
    }

    */
}
