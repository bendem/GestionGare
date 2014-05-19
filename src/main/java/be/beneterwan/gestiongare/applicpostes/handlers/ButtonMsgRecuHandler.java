package be.beneterwan.gestiongare.applicpostes.handlers;

import be.beneterwan.gestiongare.applicpostes.ApplicPostes;
import be.beneterwan.gestiongare.applicpostes.ApplicPostesFrame;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.AckMessage;
import java.util.EventObject;
/**
 * @author bendem & Curlybear
 */
public class ButtonMsgRecuHandler implements EventHandler {

    private final ApplicPostes applicPostes;
    private final ApplicPostesFrame applicPostesFrame;

    public ButtonMsgRecuHandler(ApplicPostes applicPostes) {
        this.applicPostes = applicPostes;
        this.applicPostesFrame = applicPostes.getFrame();
    }

    @Override
    public void execute(EventObject event) {
        if(applicPostesFrame.getTrainAnnonce() != null) {
            applicPostesFrame.addTrainConsidere(applicPostesFrame.getTrainAnnonce());
            applicPostesFrame.setTrainAnnonce(null);
            applicPostesFrame.getFieldAnnonce().setText("");
            applicPostesFrame.getFieldTrainConsidere().setText(applicPostesFrame.getTrainConsidere().toString());
            applicPostesFrame.getButtonSignalPassageTrain().setEnabled(true);
            applicPostesFrame.getButtonMsgRecu().setEnabled(false);

            AckMessage message = new AckMessage();
            message.send(applicPostes.getNetworkSender());
        }
    }
}
