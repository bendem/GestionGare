package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.Train;

/**
 * @author bendem & Curlybear
 */
public class CreatedNewTrainMessage extends Message {

    private final Train train;

    public CreatedNewTrainMessage(Train train) {
        super(Type.CreatedNewTrain);
        this.train = train;
    }

}
