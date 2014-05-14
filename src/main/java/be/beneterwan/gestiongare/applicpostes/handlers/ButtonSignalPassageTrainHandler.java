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
        applicPostes.getFrame().addTrainPartis(applicPostes.getFrame().getTrainConsidere());
        TrainTransited message = new TrainTransited(applicPostes.getFrame().withdrawTrainConsidere());
        if(applicPostes.getFrame().getTrainConsidere() != null) {
            applicPostes.getFrame().getFieldTrainConsidere().setText(applicPostes.getFrame().getTrainConsidere().toString());
        } else {
            applicPostes.getFrame().getFieldTrainConsidere().setText("");
            applicPostes.getFrame().getButtonSignalPassageTrain().setEnabled(false);
        }
        message.send(applicPostes.getNetworkSender());
    }
}
