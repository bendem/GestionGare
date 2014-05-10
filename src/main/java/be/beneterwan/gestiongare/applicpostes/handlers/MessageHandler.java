package be.beneterwan.gestiongare.applicpostes.handlers;

import be.beneterwan.gestiongare.applicpostes.ApplicPostes;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.Message;
import be.beneterwan.gestiongare.commons.network.messages.Message.Type;
import be.beneterwan.gestiongare.commons.network.messages.TrainMessage;
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
        Message message;
        MessageEvent mEvent = (MessageEvent)event;

        message = mEvent.getMessage();

        if(message.getType().equals(Type.TrainComing)){
            applicPostes.getFrame().setTrainAnnonce(((TrainMessage)message).getTrain());
            applicPostes.getFrame().getFieldAnnonce().setText(applicPostes.getFrame().getTrainAnnonce().toString());
        }
    }

}
