package be.beneterwan.gestiongare.applicdepot.handlers;

import be.beneterwan.gestiongare.applicdepot.ApplicDepot;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.Message;
import be.beneterwan.gestiongare.commons.network.messages.TrainMessage;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import be.beneterwan.gestiongare.commons.trains.Train;
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
        Message message = ((MessageEvent) event).getMessage();
        if(!message.getType().equals(Message.Type.Ack)) {
            Train train = ((TrainMessage) message).getTrain();
            applicDepot.getFrame().getFieldAnnonce().setText(train.toString());
        }
    }

}
