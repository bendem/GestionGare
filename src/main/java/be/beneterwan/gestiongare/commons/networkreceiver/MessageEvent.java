package be.beneterwan.gestiongare.commons.networkreceiver;

import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class MessageEvent extends EventObject {

    private final String message;

    public MessageEvent(String message, Object source) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
