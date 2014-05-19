package be.beneterwan.gestiongare.applicpostes.handlers;

import be.beneterwan.gestiongare.applicpostes.ApplicPostes;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.HoraireTrainTransitedMessage;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
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

        // The train manager need a train with HoraireTrain.State.Inbound for
        // consistency, so we set it here before sending it.
        // This is necessary because the train which was set as Inbound is a clone
        // (because of the network travel) and this one has therefore not been set
        // when using TrainManager#setCurrentTrainInbound().
        HoraireTrain trainConsidere = applicPostes.getFrame().withdrawTrainConsidere();
        trainConsidere.setState(HoraireTrain.State.Inbound);
        HoraireTrainTransitedMessage message = new HoraireTrainTransitedMessage(trainConsidere);

        if(applicPostes.getFrame().getTrainConsidere() != null) {
            applicPostes.getFrame().getFieldTrainConsidere().setText(applicPostes.getFrame().getTrainConsidere().toString());
        } else {
            applicPostes.getFrame().getFieldTrainConsidere().setText("");
            applicPostes.getFrame().getButtonSignalPassageTrain().setEnabled(false);
        }
        message.send(applicPostes.getNetworkSender());
    }
}
