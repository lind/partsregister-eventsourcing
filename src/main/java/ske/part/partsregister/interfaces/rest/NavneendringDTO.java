package ske.part.partsregister.interfaces.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NavneendringDTO {

    @XmlElement()
    private String id;

    @XmlElement()
    private String fornavn;

    @XmlElement()
    private String etternavn;

    // Moxy trenger denne...
    public NavneendringDTO() {
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
