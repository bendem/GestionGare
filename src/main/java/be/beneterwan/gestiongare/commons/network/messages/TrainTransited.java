package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.HoraireTrain;

/**
 * @author bendem & Curlybear
 */
public class TrainTransited extends TrainMessage {

    public TrainTransited(HoraireTrain train) {
        super(train, Type.TrainTransited);
    }

}
