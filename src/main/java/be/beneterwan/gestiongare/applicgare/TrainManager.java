package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain.State;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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
    private final List<HoraireTrain> inboundTrains;
    private final LinkedList<HoraireTrain> outboundTrains;
    private HoraireTrain current;

    public TrainManager() {
        incomingTrains = new LinkedList<>();
        try {
            // Loading train list
            LOGGER.info("Loading schedule...");
            incomingTrains.addAll((Collection<HoraireTrain>) new ObjectLoader("./schedules.dat").load());
        } catch(IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Could not load trains schedule, exiting...", ex);
            System.exit(0);
        }
        inboundTrains = new ArrayList<>();
        outboundTrains = new LinkedList<>();
        
        
    }

    // TODO Fucking load of state check and exceptions

    public HoraireTrain nextTrain() {
        current = incomingTrains.poll();
        return current;
    }

    public void setCurrentTrainInbound() {
        current.setState(State.Inbound);
        inboundTrains.add(current);
    }

    public void trainArrived(HoraireTrain horaire) {
        current.setState(State.Stationned);
        inboundTrains.add(horaire);
    }

    public void trainLeaving(HoraireTrain horaire) {
        current.setState(State.Leaving);
        inboundTrains.add(horaire);
    }

    public void trainLeft(HoraireTrain horaire) {
        inboundTrains.remove(horaire);
        outboundTrains.add(horaire);
        saveOutboundTrains();
    }

    public HoraireTrain getCurrent() {
        return current;
    }

    public List<HoraireTrain> getInboundTrains() {
        return inboundTrains;
    }

    private void saveOutboundTrains() {
        // TODO
    }
    
}
