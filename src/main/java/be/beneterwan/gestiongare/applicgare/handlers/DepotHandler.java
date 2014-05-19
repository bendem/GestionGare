package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.HoraireTrainComingMessage;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class DepotHandler implements EventHandler {

    private final ApplicGare applicGare;

    public DepotHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
        HoraireTrainComingMessage message = new HoraireTrainComingMessage(applicGare.getTrainManager().getCurrent());
        message.send(applicGare.getDepotNetworkSender());

        applicGare.getFrame().getButtonControleIn().setEnabled(false);
        applicGare.getFrame().getButtonDepot().setEnabled(false);
    }
}
