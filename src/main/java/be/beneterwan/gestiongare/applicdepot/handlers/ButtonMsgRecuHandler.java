package be.beneterwan.gestiongare.applicdepot.handlers;

import be.beneterwan.gestiongare.applicdepot.ApplicDepot;
import be.beneterwan.gestiongare.applicdepot.ApplicDepotFrame;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.Ack;
import java.util.EventObject;

/**
 * @author Bear
 */
public class ButtonMsgRecuHandler implements EventHandler {

    private final ApplicDepot applicDepot;
    private final ApplicDepotFrame applicDepotFrame;

    public ButtonMsgRecuHandler(ApplicDepot applicDepot) {
        this.applicDepot = applicDepot;
        this.applicDepotFrame = applicDepot.getFrame();
    }

    @Override
    public void execute(EventObject event) {
        if(applicDepotFrame.getTrainAnnonce() != null) {
            applicDepotFrame.addTrainConsidere(applicDepotFrame.getTrainAnnonce());
            applicDepotFrame.setTrainAnnonce(null);
            applicDepotFrame.getFieldAnnonce().setText("");
            applicDepotFrame.getFieldTrainConsidere().setText(applicDepotFrame.getTrainConsidere().toString());
            applicDepotFrame.getButtonSignalArriveeTrain().setEnabled(true);
            applicDepotFrame.getButtonMsgRecu().setEnabled(false);

            Ack message = new Ack();
            message.send(applicDepot.getNetworkSender());
        }
    }
}
