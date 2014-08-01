package ske.part.partsregister.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PartDTO {

    private String fornavn;
    private String etternavn;

    public PartDTO(String fornavn, String etternavn) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
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
