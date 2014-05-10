package be.beneterwan.gestiongare.commons.network.receiver;

import be.beneterwan.gestiongare.commons.network.messages.Message;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class MessageEvent extends EventObject {

    private final Message message;

    public MessageEvent(Message message, Object source) {
        super(source);
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

}
