package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import serialize.ObjectLoader;

/**
 * @author bendem & Curlybear
 */
public class TrainManager {

    private static final Logger LOGGER = new CustomLogger(ApplicGareFrame.class.getSimpleName());

    private final Queue<HoraireTrain> incomingTrains;
    private final Map<HoraireTrain, State> inboundTrains;
    private final LinkedList<HoraireTrain> outboundTrains;
    private HoraireTrain current;

    public TrainManager() {
        incomingTrains = new LinkedList<>();
        try {
            // Loading train list
            LOGGER.info("Loading schedule...");
            incomingTrains.addAll((Queue<HoraireTrain>) new ObjectLoader("./schedules.dat").load());
        } catch(IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Could not load trains schedule, exiting...", ex);
            System.exit(0);
        }
        inboundTrains = new HashMap<>();
        outboundTrains = new LinkedList<>();
    }

    // TODO Fucking load of state check and exceptions

    public HoraireTrain nextTrain() {
        current = incomingTrains.poll();
        return current;
    }

    public void setCurrentTrainInbound() {
        inboundTrains.put(current, State.Inbound);
    }

    public void trainArrived(HoraireTrain horaire) {
        inboundTrains.put(horaire, State.Stationned);
    }

    public void trainLeaving(HoraireTrain horaire) {
        inboundTrains.put(horaire, State.Leaving);
    }

    public void trainLeft(HoraireTrain horaire) {
        inboundTrains.remove(horaire);
        outboundTrains.add(horaire);
        saveOutboundTrains();
    }

    private void saveOutboundTrains() {
        // TODO
    }

    public enum State {
        Inbound, Stationned, Leaving
    }

}
