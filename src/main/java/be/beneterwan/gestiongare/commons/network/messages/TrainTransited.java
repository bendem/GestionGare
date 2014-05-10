package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.Train;

/**
 * @author bendem & Curlybear
 */
public class TrainTransited extends TrainMessage {

    public TrainTransited(Train train) {
        super(train, Type.TrainTransited);
    }

}
