package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.HoraireTrain;

/**
 * @author bendem & Curlybear
 */
public class TrainComing extends TrainMessage {

    public TrainComing(HoraireTrain train) {
        super(train, Type.TrainComing);
    }

}
