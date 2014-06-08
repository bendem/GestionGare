package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.network.messages.CreatedNewTrainMessage;
import be.beneterwan.gestiongare.commons.network.messages.Message;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.EventObject;
import java.util.logging.Logger;

/**
 *
 * @author bendem & Curlybear
 */
public class MessageDepotHandler implements EventHandler {

    private static final Logger LOGGER = new CustomLogger(MessageDepotHandler.class);
    private final ApplicGare applicGare;

    public MessageDepotHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
        Message message = ((MessageEvent) event).getMessage();
        LOGGER.info("Message reçu!");

        switch(message.getType()) {
            case Ack:
                applicGare.getFrame().getFieldDepot().setText("ACK");
                applicGare.getTrainManager().storeCurrent();
                applicGare.getFrame().getButtonTrainSuivant().setEnabled(true);
                applicGare.getFrame().getButtonControleIn().setEnabled(false);
                applicGare.getFrame().getButtonDepot().setEnabled(false);
                break;
            case Stored:
                applicGare.getFrame().getFieldDepot().setText(applicGare.getTrainManager().getStoreCurrentNum());
                break;
            case CreatedNewTrain:
                // TODO
                CreatedNewTrainMessage trainMessage = (CreatedNewTrainMessage) message;
                HoraireTrain horaireTrain = new HoraireTrain(trainMessage.getTrain(), "EN ENFER!", "ici", 0, 5, 6/*?*/);
                break;
            default:
                LOGGER.severe("Unhandled depot message");
        }
    }

}
