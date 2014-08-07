package ske.part.partview.infrastructure;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import javax.inject.Singleton;

import com.google.common.base.Optional;
import ske.part.partview.interfaces.rest.PartViewDTO;
import ske.part.partview.domain.PartViewModel;

@Singleton
public class PartViewStore {

    private final Map<String, PartViewModel> partViewStore = newHashMap();

    public void opprettPart(String id, String etternavn) {
        // TODO check if exists -> log
        //        Precon partViewStore.containsKey(part.getId());
        partViewStore.put(id, new PartViewModel(id, etternavn));
    }

    public PartViewModel hentPart(String partId) {
        // TODO: check for null -> log (no ex)
        PartViewModel partViewModel = partViewStore.get(partId);

        return partViewModel;
    }

    public void endreEtternavn(String id, String etternavn) {
        PartViewModel partViewModel = partViewStore.get(id);
        partViewModel.setEtternavn(etternavn);
        partViewStore.put(id, partViewModel);
    }

}
