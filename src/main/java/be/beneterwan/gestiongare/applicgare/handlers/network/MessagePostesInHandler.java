package be.beneterwan.gestiongare.applicgare.handlers.network;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.network.messages.HoraireTrainTransitedMessage;
import be.beneterwan.gestiongare.commons.network.messages.Message;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import be.beneterwan.gestiongare.commons.trains.Train;
import java.util.EventObject;
import java.util.logging.Logger;

/**
 *
 * @author bendem & Curlybear
 */
public class MessagePostesInHandler implements EventHandler {

    private static final Logger LOGGER = new CustomLogger(MessagePostesInHandler.class);

    private final ApplicGare applicGare;

    public MessagePostesInHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
        Message message = ((MessageEvent) event).getMessage();
        LOGGER.info("Message reçu!");

        if(message.getType().equals(Message.Type.TrainTransited)){
            Train train = ((HoraireTrainTransitedMessage) message).getHoraireTrain().getTrain();
            applicGare.getFrame().getFieldControleIn().setText(train.toString());
            applicGare.getTrainManager().setCurrentTrainInbound();

        } else if(message.getType().equals(Message.Type.Ack)) {
            applicGare.getFrame().getFieldControleIn().setText("ACK");
            applicGare.getFrame().getButtonTrainSuivant().setEnabled(true);
            applicGare.getFrame().getButtonControleIn().setEnabled(false);
            applicGare.getFrame().getButtonDepot().setEnabled(false);
        }
    }

}
