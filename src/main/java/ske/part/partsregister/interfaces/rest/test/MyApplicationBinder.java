package ske.part.partsregister.interfaces.rest.test;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(TestService.class).to(TestService.class);
    }
}
