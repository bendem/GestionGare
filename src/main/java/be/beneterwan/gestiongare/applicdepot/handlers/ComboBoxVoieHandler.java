package be.beneterwan.gestiongare.applicdepot.handlers;

import be.beneterwan.gestiongare.applicdepot.ApplicDepotFrame;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class ComboBoxVoieHandler implements EventHandler {

    private final ApplicDepotFrame frame;
    public ComboBoxVoieHandler(ApplicDepotFrame frame) {
        this.frame = frame;
    }

    @Override
    public void execute(EventObject event) {

        if(frame.getStoredTrains().get(Integer.parseInt((String)frame.getComboBoxVoie().getSelectedItem()))==null) {
            frame.getButtonValider().setEnabled(true);
        } else {
            frame.getButtonValider().setEnabled(false);
        }
    }

}
