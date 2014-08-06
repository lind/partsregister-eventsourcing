package ske.part.partview.domain;

public class PartViewModel {

    private String id;
    private String fornavn;
    private String etternavn;

    public PartViewModel(String id, String fornavn, String etternavn) {
        this.id = id;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public String getId() {
        return id;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getFornavn() {
        return fornavn;
    }

}
