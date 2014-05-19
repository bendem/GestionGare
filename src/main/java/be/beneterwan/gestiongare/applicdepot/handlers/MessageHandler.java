package be.beneterwan.gestiongare.applicdepot.handlers;

import be.beneterwan.gestiongare.applicdepot.ApplicDepot;
import be.beneterwan.gestiongare.applicdepot.OccupationHangarTableModel;
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

        if(message.getType().equals(Message.Type.TrainComing)) {
            HoraireTrain horaire = ((HoraireTrainMessage) message).getHoraireTrain();
            applicDepot.getFrame().setTrainAnnonce(horaire);
            applicDepot.getFrame().getButtonMsgRecu().setEnabled(true);
            applicDepot.getFrame().getFieldAnnonce().setText(horaire.toString());

        } else if(message.getType().equals(Message.Type.CreateNewTrain)) {
            CreatedNewTrainMessage toSend = new CreatedNewTrainMessage();

            for(Map.Entry<Integer, HoraireTrain> entry : applicDepot.getFrame().getStoredTrains().entrySet()) {
                HoraireTrain value = entry.getValue();

                if(value != null) {
                    toSend.setTrain(value.getTrain());
                    applicDepot.getFrame().getStoredTrains().put(entry.getKey(), null);
                    ((OccupationHangarTableModel) applicDepot.getFrame().getTableOccupationHangar().getModel()).fireTableDataChanged();
                    break;
                }
            }
            toSend.send(applicDepot.getNetworkSender());
        }
    }

}
