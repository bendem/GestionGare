package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.network.messages.Message;
import be.beneterwan.gestiongare.commons.network.messages.TrainTransited;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import be.beneterwan.gestiongare.commons.trains.Train;
import java.util.EventObject;
import java.util.logging.Logger;

/**
 *
 * @author bendem & Curlybear
 */
public class MessagePostesOutHandler implements EventHandler{

    private static final Logger LOGGER = new CustomLogger(MessagePostesInHandler.class.getSimpleName());
    private final ApplicGare applicGare;

    public MessagePostesOutHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {        
        Message message = ((MessageEvent) event).getMessage();
        LOGGER.info("Message reçu!");

        if(message.getType().equals(Message.Type.TrainTransited)){
            Train train = ((TrainTransited) message).getHoraireTrain().getTrain();
            applicGare.getFrame().getFieldControleIn().setText(train.toString());
            applicGare.getTrainManager().trainLeft(((TrainTransited) message).getHoraireTrain());
            
        } else if(message.getType().equals(Message.Type.Ack)) {
            applicGare.getTrainManager().trainLeaving(applicGare.getTrainManager().getOutCurrent());
            applicGare.getFrame().getFieldControleIn().setText("ACK");
            applicGare.getFrame().getButtonDepot().setEnabled(true);
        }
    }

}
