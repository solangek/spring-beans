package hac.springbeans.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;

/* this is a simple bean class instantiated in session */

@Component
public class Messages implements Serializable {
    private ArrayList<Label> messages;

    public Messages() {
        this.messages = new ArrayList<>();
    }

    public ArrayList<Label>  getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Label>  messages) {
        this.messages = messages;
    }

    public void add (Label m) {
        messages.add(m);
    }

}