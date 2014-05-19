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
        StoredMessage message = new StoredMessage();
        if(applicDepot.getFrame().getTrainConsidere() != null) {
            applicDepot.getFrame().getFieldTrainConsidere().setText(applicDepot.getFrame().getTrainConsidere().toString());
        } else {
            applicDepot.getFrame().getFieldTrainConsidere().setText("");
            applicDepot.getFrame().getButtonSignalArriveeTrain().setEnabled(false);
        }
        message.send(applicDepot.getNetworkSender());
    }
}
