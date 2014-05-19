package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.HoraireTrain;

/**
 * @author bendem & Curlybear
 */
public class HoraireTrainTransitedMessage extends HoraireTrainMessage {

    public HoraireTrainTransitedMessage(HoraireTrain train) {
        super(train, Type.TrainTransited);
    }

}
