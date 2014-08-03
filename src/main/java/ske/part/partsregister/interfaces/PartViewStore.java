package ske.part.partsregister.interfaces;

import com.google.common.base.Optional;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class PartViewStore {

    private final Map<String, PartViewModel> partViewStore = newHashMap();

    public Optional<PartDTO> hentPart(String partId) {
        // TODO: check for null -> log (no ex)
        PartViewModel partViewModel = partViewStore.get(partId);

        return (null != partViewModel ? Optional.of(new PartDTO(partViewModel.getId(), partViewModel.getFornavn(), partViewModel.getEtternavn())) :
                Optional.<PartDTO>absent());
    }

    public void opprettPart(PartViewModel part) {
        // TODO check if exists -> log
//        Precon partViewStore.containsKey(part.getId());

        partViewStore.put(part.getId(), part);
    }
}
