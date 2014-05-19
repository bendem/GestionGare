package be.beneterwan.gestiongare.applicpostes.handlers;

import be.beneterwan.gestiongare.applicpostes.ApplicPostes;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.Message;
import be.beneterwan.gestiongare.commons.network.messages.Message.Type;
import be.beneterwan.gestiongare.commons.network.messages.HoraireTrainMessage;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
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
        Message message = ((MessageEvent) event).getMessage();
        HoraireTrain horaire = ((HoraireTrainMessage) message).getHoraireTrain();

        if(message.getType().equals(Type.TrainComing)){
            applicPostes.getFrame().setTrainAnnonce(horaire);
            applicPostes.getFrame().getButtonMsgRecu().setEnabled(true);
            applicPostes.getFrame().getFieldAnnonce().setText(horaire.toString());
        }
    }

}
