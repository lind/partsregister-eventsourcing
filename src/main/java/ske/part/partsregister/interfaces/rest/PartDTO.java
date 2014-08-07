package ske.part.partsregister.interfaces.rest;

public class PartDTO {

    private String id;
    private String fornavn;
    private String etternavn;

    public PartDTO(String id, String fornavn, String etternavn) {
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
