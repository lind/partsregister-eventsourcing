package ske.part.partview.infrastructure;

import javax.inject.Singleton;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.part.partsregister.domain.part.PersonOpprettetEvent;
import ske.part.partview.domain.PartViewModel;

@Singleton
public class PartEventHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private PartViewStore viewStore;

    public PartEventHandler(PartViewStore viewStore) {
        this.viewStore = viewStore;
    }

    @Subscribe
    public void handlePersonOpprettetEvent(PersonOpprettetEvent event) {
        logger.debug("handlePersonOpprettetEvent - Fornavn: {}", event.getFornavn());
        viewStore.opprettPart(new PartViewModel(event.getEventSourceIdentifier().asString(), event.getFornavn(),
                event.getEtternavn()));
    }
}
