package ske.part.partview.interfaces.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PartDTO {

    private String id;
    private String fornavn;
    private String etternavn;

    public PartDTO(String id, String fornavn, String etternavn) {
        this.id = id;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getEtternavn() {
        return etternavn;
    }

    @JsonProperty
    public String getFornavn() {
        return fornavn;
    }
}
