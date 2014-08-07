package ske.part.partview.domain;

public class PartViewModel {

    private String id;
    private String etternavn;

    public PartViewModel(String id, String etternavn) {
        this.id = id;
        this.etternavn = etternavn;
    }

    public String getId() {
        return id;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }
}
