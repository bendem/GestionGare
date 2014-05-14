package be.beneterwan.gestiongare.applicdepot.handlers;

import be.beneterwan.gestiongare.applicdepot.ApplicDepot;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.Stored;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class ButtonSignalPassageTrainHandler implements EventHandler {

    private final ApplicDepot applicDepot;

    public ButtonSignalPassageTrainHandler(ApplicDepot applicPostes) {
        this.applicDepot = applicPostes;
    }

    @Override
    public void execute(EventObject event) {
        Stored message = new Stored();
        if(applicDepot.getFrame().getTrainConsidere() != null) {
            applicDepot.getFrame().getFieldTrainConsidere().setText(applicDepot.getFrame().getTrainConsidere().toString());
        } else {
            applicDepot.getFrame().getFieldTrainConsidere().setText("");
            applicDepot.getFrame().getButtonSignalPassageTrain().setEnabled(false);
        }
        message.send(applicDepot.getNetworkSender());
    }
}
