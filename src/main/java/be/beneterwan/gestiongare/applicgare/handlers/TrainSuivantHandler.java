package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class TrainSuivantHandler implements EventHandler {

    private final ApplicGare applicGare;

    public TrainSuivantHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
        HoraireTrain train = applicGare.getTrainManager().nextTrain();

        if(train != null) {
            applicGare.getFrame().getFieldProchainTrain().setText(train.toString());
            applicGare.getFrame().getButtonTrainSuivant().setEnabled(false);
        } else {
            applicGare.getFrame().getFieldProchainTrain().setText("Pas de train disponible.");
        }
    }
}
