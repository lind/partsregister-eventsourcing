package ske.part.partview.infrastructure;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;
import javax.inject.Singleton;

import ske.part.partview.domain.PartViewModel;

@Singleton
public class PartViewStore {

    private final Map<String, PartViewModel> partViewStore = newHashMap();

    public void opprettPart(String id, String etternavn) {
        partViewStore.put(id, new PartViewModel(id, etternavn));
    }

    public PartViewModel hentPart(String partId) {
        return partViewStore.get(partId);
    }

    public void endreEtternavn(String id, String etternavn) {
        PartViewModel partViewModel = partViewStore.get(id);
        partViewModel.setEtternavn(etternavn);
        partViewStore.put(id, partViewModel);
    }

}
