package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.network.messages.Message;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import java.util.EventObject;
import java.util.logging.Logger;

/**
 *
 * @author bendem & Curlybear
 */
public class MessageDepotHandler implements EventHandler {

    private static final Logger LOGGER = new CustomLogger(MessageDepotHandler.class.getSimpleName());
    private final ApplicGare applicGare;

    public MessageDepotHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
        Message message = ((MessageEvent) event).getMessage();
        LOGGER.info("Message reçu!");

        if(message.getType().equals(Message.Type.Ack)) {
            applicGare.getFrame().getFieldDepot().setText("ACK");
            applicGare.getTrainManager().storeCurrent();
            applicGare.getFrame().getButtonTrainSuivant().setEnabled(true);
            applicGare.getFrame().getButtonControleIn().setEnabled(false);
            applicGare.getFrame().getButtonDepot().setEnabled(false);
        } else if(message.getType().equals(Message.Type.Stored)) {
            applicGare.getFrame().getFieldDepot().setText(applicGare.getTrainManager().getStoreCurrentNum());
        }
    }

}
