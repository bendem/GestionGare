package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.HoraireTrain;

/**
 * @author bendem & Curlybear
 */
public class TrainMessage extends Message {

    private final HoraireTrain train;

    public TrainMessage(HoraireTrain train, Type type) {
        super(type);
        this.train = train;
    }

    public HoraireTrain getHoraireTrain() {
        return train;
    }

}
