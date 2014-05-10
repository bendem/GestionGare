package be.beneterwan.gestiongare.applicdepot.handlers;

import be.beneterwan.gestiongare.applicdepot.ApplicDepot;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.Ack;
import java.util.EventObject;

/**
 *
 * @author Bear
 */
public class ButtonMsgRecuHandler implements EventHandler{

    private final ApplicDepot applicDepot;

    public ButtonMsgRecuHandler(ApplicDepot applicDepot) {
        this.applicDepot = applicDepot;
    }

    @Override
    public void execute(EventObject event) {
        Ack message = new Ack();
        message.send(applicDepot.getNetworkSender());
    }
}
