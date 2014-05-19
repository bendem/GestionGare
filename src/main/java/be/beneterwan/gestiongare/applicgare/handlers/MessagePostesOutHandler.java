package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.network.messages.Message;
import be.beneterwan.gestiongare.commons.network.messages.HoraireTrainTransitedMessage;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import be.beneterwan.gestiongare.commons.trains.Train;
import java.util.EventObject;
import java.util.logging.Logger;

/**
 *
 * @author bendem & Curlybear
 */
public class MessagePostesOutHandler implements EventHandler {

    private static final Logger LOGGER = new CustomLogger(MessagePostesOutHandler.class);
    private final ApplicGare applicGare;

    public MessagePostesOutHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
        Message message = ((MessageEvent) event).getMessage();
        LOGGER.info("Message reçu!");

        if(message.getType().equals(Message.Type.TrainTransited)){
            Train train = ((HoraireTrainTransitedMessage) message).getHoraireTrain().getTrain();
            applicGare.getFrame().getFieldControleOut().setText(train.toString());
            applicGare.getTrainManager().trainLeft(((HoraireTrainTransitedMessage) message).getHoraireTrain());

        } else if(message.getType().equals(Message.Type.Ack)) {
            applicGare.getTrainManager().trainLeaving(applicGare.getTrainManager().getOutCurrent());
            applicGare.getFrame().getFieldControleOut().setText("ACK");
            applicGare.getFrame().getButtonDepot().setEnabled(true);
        }
    }

}
