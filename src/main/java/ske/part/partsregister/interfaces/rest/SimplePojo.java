package ske.part.partsregister.interfaces.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimplePojo {

    @XmlElement()
    private String navn;

    @XmlElement(name = "Alder")
    private int alder;

    // Moxy trenger denne...
    public SimplePojo() {
    }

    public SimplePojo(String navn, int alder) {
        this.navn = navn;
        this.alder = alder;
    }

    public String getNavn() {
        return navn;
    }

    public int getAlder() {
        return alder;
    }
}
