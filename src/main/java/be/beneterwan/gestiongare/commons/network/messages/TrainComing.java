package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.Train;

/**
 * @author bendem & Curlybear
 */
public class TrainComing extends TrainMessage {

    public TrainComing(Train train) {
        super(train, Type.TrainComing);
    }

}
