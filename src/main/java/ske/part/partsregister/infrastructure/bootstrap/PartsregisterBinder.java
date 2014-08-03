package ske.part.partsregister.infrastructure.bootstrap;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ske.part.partsregister.interfaces.rest.test.TestService;

public class PartsregisterBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(TestService.class).to(TestService.class);
    }

}
