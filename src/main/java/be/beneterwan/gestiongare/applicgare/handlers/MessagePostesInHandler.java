package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.Message;
import be.beneterwan.gestiongare.commons.network.messages.TrainTransited;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import be.beneterwan.gestiongare.commons.trains.Train;
import java.util.EventObject;

/**
 *
 * @author bendem & Curlybear
 */
public class MessagePostesInHandler implements EventHandler{

    private final ApplicGare applicGare;

    public MessagePostesInHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
        Message message = ((MessageEvent) event).getMessage();

        if(message.getType().equals(Message.Type.TrainTransited)){
            Train train = ((TrainTransited) message).getHoraireTrain().getTrain();
            applicGare.getFrame().getFieldControleIn().setText(train.toString());
        } else if(message.getType().equals(Message.Type.Ack)) {
            applicGare.getTrainManager().setCurrentTrainInbound();

            applicGare.getFrame().getButtonTrainSuivant().setEnabled(true);
            applicGare.getFrame().getButtonControleIn().setEnabled(false);
            applicGare.getFrame().getButtonDepot().setEnabled(false);
        }
    }

}
