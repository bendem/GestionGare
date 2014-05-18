package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.EventObject;
import javax.swing.event.ListSelectionEvent;

/**
 * @author bendem & Curlybear
 */
public class TableSelectionChangedHandler implements EventHandler {

    private final ApplicGare applicGare;
    private final ApplicGareFrame frame;

    public TableSelectionChangedHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
        frame = applicGare.getFrame();
    }

    @Override
    public void execute(EventObject event) {
        if (((ListSelectionEvent) event).getValueIsAdjusting()) {
            return;
        }

        int selectedRow = frame.getTableOccupationVoies().getSelectedRow() + 1;
        HoraireTrain horaire = applicGare.getTrainManager().getInboundTrains().get(selectedRow);
        // Button is enabled if there's a train on the platform
        boolean enabled = horaire != null && horaire.getState().equals(HoraireTrain.State.Stationned);
        frame.getButtonControleOut().setEnabled(enabled);
    }

}
