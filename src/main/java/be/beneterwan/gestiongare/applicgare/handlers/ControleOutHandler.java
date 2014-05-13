package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
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
        HoraireTrain train;
        
        if ( applicGare.getFrame().getTableOccupationVoies().getSelectedRow() != -1) {
            train=applicGare.getTrainManager().getInboundTrains().get(id);
            if (train.getState()== HoraireTrain.State.Stationned) {
                applicGare.getTrainManager().trainLeaving(train);
            }
        }
    }
}
