package be.beneterwan.gestiongare.applicdepot.handlers;

import be.beneterwan.gestiongare.applicdepot.ApplicDepot;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.CreatedNewTrainMessage;
import be.beneterwan.gestiongare.commons.network.messages.HoraireTrainMessage;
import be.beneterwan.gestiongare.commons.network.messages.Message;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.EventObject;
import java.util.Map;

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
        HoraireTrain horaire = ((HoraireTrainMessage) message).getHoraireTrain();

        if(message.getType().equals(Message.Type.TrainComing)){
            applicDepot.getFrame().setTrainAnnonce(horaire);
            applicDepot.getFrame().getButtonMsgRecu().setEnabled(true);
            applicDepot.getFrame().getFieldAnnonce().setText(horaire.toString());
        } else if(message.getType().equals(Message.Type.CreateNewTrain)){
            for(Map.Entry<Integer, HoraireTrain> en : applicDepot.getFrame().getStoredTrains().entrySet()) {
                Integer object = en.getKey();
                HoraireTrain object1 = en.getValue();

                if(object1 != null) {
                    CreatedNewTrainMessage mess = new CreatedNewTrainMessage(object1.getTrain());
                    mess.send(applicDepot.getNetworkSender());
                    applicDepot.getFrame().getStoredTrains().put(object, null);
                    return;
                }

            }
        }
    }

}
