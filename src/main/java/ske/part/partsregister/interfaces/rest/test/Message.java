package ske.part.partsregister.interfaces.rest.test;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
    private String content;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
