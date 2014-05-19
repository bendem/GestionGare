package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.HoraireTrain;

/**
 * @author bendem & Curlybear
 */
public class HoraireTrainComingMessage extends HoraireTrainMessage {

    public HoraireTrainComingMessage(HoraireTrain train) {
        super(train, Type.TrainComing);
    }

}
