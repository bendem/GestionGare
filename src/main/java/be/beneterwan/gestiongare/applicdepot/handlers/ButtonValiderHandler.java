package be.beneterwan.gestiongare.applicdepot.handlers;

import be.beneterwan.gestiongare.applicdepot.ApplicDepotFrame;
import be.beneterwan.gestiongare.applicdepot.OccupationHangarTableModel;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class ButtonValiderHandler implements EventHandler {

    private final ApplicDepotFrame frame;
    public ButtonValiderHandler(ApplicDepotFrame frame) {
        this.frame = frame;
    }

    @Override
    public void execute(EventObject event) {
        frame.getStoredTrains().put((int) frame.getComboBoxVoie().getSelectedItem(), frame.withdrawTrainConsidere());
        ((OccupationHangarTableModel) frame.getTableOccupationHangar().getModel()).fireTableDataChanged();
        frame.getButtonValider().setEnabled(false);
        frame.getComboBoxVoie().setEnabled(false);
    }

}