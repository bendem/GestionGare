package be.beneterwan.gestiongare.applicpostes.handlers;

import be.beneterwan.gestiongare.applicpostes.ApplicPostes;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class MessageHandler implements EventHandler {

    private final ApplicPostes applicPostes;

    public MessageHandler(ApplicPostes applicPostes) {
        this.applicPostes = applicPostes;
    }

    @Override
    public void execute(EventObject event) {
        MessageEvent mEvent = (MessageEvent)event;

        applicPostes.getFrame().getFieldAnnonce().setText(mEvent.getMessage());
    }

}
