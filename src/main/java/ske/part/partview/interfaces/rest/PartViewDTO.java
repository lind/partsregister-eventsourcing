package ske.part.partview.interfaces.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PartViewDTO {

    @XmlElement()
    private String id;
    @XmlElement()
    private String etternavn;

    public PartViewDTO() {
    }

    public PartViewDTO(String id, String etternavn) {
        this.id = id;
        this.etternavn = etternavn;
    }

    public String getId() {
        return id;
    }

    public String getEtternavn() {
        return etternavn;
    }

}
