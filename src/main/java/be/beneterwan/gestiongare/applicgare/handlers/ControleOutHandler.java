package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.TrainComing;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class ControleOutHandler implements EventHandler {

    private final ApplicGare applicGare;

    public ControleOutHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
        int id = applicGare.getFrame().getTableOccupationVoies().getSelectedRow();
        
        if (id != -1) {
            HoraireTrain train = applicGare.getTrainManager().getInboundTrains().get(id+1);
            if (train.getState().equals(HoraireTrain.State.Stationned)) {
                TrainComing message = new TrainComing(train);
                applicGare.getTrainManager().setOutCurrent(train);
                message.send(applicGare.getPostesOutNetworkSender());
            }
        }
    }
}
