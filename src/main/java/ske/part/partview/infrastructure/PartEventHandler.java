package ske.part.partview.infrastructure;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ske.part.partsregister.domain.part.EtternavnEndretEvent;
import ske.part.partsregister.domain.part.PersonOpprettetEvent;

public class PartEventHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private PartViewStore viewStore;

    public PartEventHandler(PartViewStore viewStore) {
        this.viewStore = viewStore;
    }

    @Subscribe
    public void handlePersonOpprettetEvent(PersonOpprettetEvent event) {
        logger.debug("handlePersonOpprettetEvent - Fornavn: {}", event.getFornavn());
        viewStore.opprettPart(event.getEventSourceIdentifier().asString(), event.getEtternavn());
    }

    @Subscribe
    public void handleEtternavnEndretEvent(EtternavnEndretEvent event) {
        logger.debug("handleEtternavnEndretEvent - Etternavn: {}", event.getEtternavn());
        viewStore.endreEtternavn(event.getEventSourceIdentifier().asString(), event.getEtternavn());
    }
}
