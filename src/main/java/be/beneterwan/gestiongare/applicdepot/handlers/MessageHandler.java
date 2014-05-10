package be.beneterwan.gestiongare.applicdepot.handlers;

import be.beneterwan.gestiongare.applicdepot.ApplicDepot;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import java.util.EventObject;

/**
 *
 * @author bendem & Curlybear
 */
public class MessageHandler implements EventHandler {

    private final ApplicDepot applicDepot;

    public MessageHandler(ApplicDepot applicDepot) {
        this.applicDepot = applicDepot;
    }

    @Override
    public void execute(EventObject event) {
        MessageEvent mEvent = (MessageEvent)event;

        applicDepot.getFrame().getFieldAnnonce().setText(mEvent.getMessage());
    }

}
