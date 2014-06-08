package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.Train;

/**
 * @author bendem & Curlybear
 */
public class CreatedNewTrainMessage extends Message {

    private Train train;

    public CreatedNewTrainMessage() {
        this(null);
    }

    public CreatedNewTrainMessage(Train train) {
        super(Type.CreatedNewTrain);
        this.train = train;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

}
