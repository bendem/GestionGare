package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.Train;

/**
 * @author bendem & Curlybear
 */
public class TrainMessage extends Message {

    private final Train train;

    public TrainMessage(Train train, Type type) {
        super(type);
        this.train = train;
    }

    public Train getTrain() {
        return train;
    }

}
