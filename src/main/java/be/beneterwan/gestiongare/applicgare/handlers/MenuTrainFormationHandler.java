package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.CreateNewTrainMessage;
import be.beneterwan.gestiongare.commons.network.messages.Message;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class MenuTrainFormationHandler implements EventHandler {

    private final ApplicGare applicGare;

    public MenuTrainFormationHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
        Message message = new CreateNewTrainMessage();
        message.send(applicGare.getDepotNetworkSender());
    }

}
