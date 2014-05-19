package be.beneterwan.gestiongare.applicdepot.handlers;

import be.beneterwan.gestiongare.applicdepot.ApplicDepot;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.StoredMessage;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class ButtonSignalArriveeTrainHandler implements EventHandler {

    private final ApplicDepot applicDepot;

    public ButtonSignalArriveeTrainHandler(ApplicDepot applicPostes) {
        this.applicDepot = applicPostes;
    }

    @Override
    public void execute(EventObject event) {
        if(applicDepot.getFrame().getTrainConsidere() != null) {
            StoredMessage message = new StoredMessage();
            applicDepot.getFrame().getFieldTrainConsidere().setText(applicDepot.getFrame().getTrainConsidere().toString());
            applicDepot.getFrame().getButtonSignalArriveeTrain().setEnabled(false);
            applicDepot.getFrame().getComboBoxVoie().setEnabled(true);
            message.send(applicDepot.getNetworkSender());
        }
    }

}
