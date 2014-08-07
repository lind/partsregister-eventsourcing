package ske.part.partsregister.interfaces.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EndreNavnDTO {

    @XmlElement()
    private String fornavn;

    @XmlElement()
    private String etternavn;

    // Moxy trenger denne...
    public EndreNavnDTO() {
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }
}
