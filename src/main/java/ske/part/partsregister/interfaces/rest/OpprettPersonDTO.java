package ske.part.partsregister.interfaces.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OpprettPersonDTO {

    @XmlElement()
    private String id;
    @XmlElement()
    private String fornavn;
    @XmlElement()
    private String etternavn;

    // Moxy trenger denne...
    public OpprettPersonDTO() {
    }

    public OpprettPersonDTO(String id, String fornavn, String etternavn) {
        this.id = id;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public String getId() {
        return id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }
}
