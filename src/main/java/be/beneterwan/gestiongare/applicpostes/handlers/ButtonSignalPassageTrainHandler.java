package be.beneterwan.gestiongare.applicpostes.handlers;

import be.beneterwan.gestiongare.applicpostes.ApplicPostes;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.TrainTransited;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class ButtonSignalPassageTrainHandler implements EventHandler {

    private final ApplicPostes applicPostes;

    public ButtonSignalPassageTrainHandler(ApplicPostes applicPostes) {
        this.applicPostes = applicPostes;
    }

    @Override
    public void execute(EventObject event) {
        TrainTransited message = new TrainTransited(applicPostes.getFrame().getTrainConsidere());
        applicPostes.getFrame().setTrainConsidere(null);
        applicPostes.getFrame().getFieldAnnonce().setText("");
        message.send(applicPostes.getNetworkSender());
    }
}
