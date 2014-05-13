package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain.State;
import java.io.IOException;
import java.util.Collection;
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

    public static final int NB_VOIES = 8;

    private final Queue<HoraireTrain> incomingTrains;
    private final Map<Integer, HoraireTrain> inboundTrains;
    private final LinkedList<HoraireTrain> outboundTrains;
    private final ApplicGare applicGare;
    private HoraireTrain current;

    public TrainManager(ApplicGare applicGare) {
        incomingTrains = new LinkedList<>();
        try {
            // Loading train list
            LOGGER.info("Loading schedule...");
            incomingTrains.addAll((Collection<HoraireTrain>) new ObjectLoader("./schedules.dat").load());
        } catch(IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Could not load trains schedule, exiting...", ex);
            System.exit(0);
        }
        inboundTrains = new HashMap<>();
        outboundTrains = new LinkedList<>();
        this.applicGare = applicGare;
        initTable();
    }

    // TODO Fucking load of state check and exceptions

    public HoraireTrain nextTrain() {
        current = incomingTrains.poll();
        return current;
    }

    public void setCurrentTrainInbound() {
        current.setState(State.Inbound);
        inboundTrains.put(current.getQuai(), current);
        updateTable();
    }

    public void trainArrived(HoraireTrain horaire) {
        horaire.setState(State.Stationned);
        inboundTrains.put(horaire.getQuai(), horaire);
        updateTable();
    }

    public void trainLeaving(HoraireTrain horaire) {
        horaire.setState(State.Leaving);
        inboundTrains.put(horaire.getQuai(), horaire);
        updateTable();
    }

    public void trainLeft(HoraireTrain horaire) {
        outboundTrains.add(horaire);
        saveOutboundTrains();
        updateTable();
    }

    public HoraireTrain getCurrent() {
        return current;
    }

    public Map<Integer, HoraireTrain> getInboundTrains() {
        return inboundTrains;
    }

    private void saveOutboundTrains() {
        // TODO
    }

    private void updateTable() {
        ((OccupationVoiesTableModel) applicGare.getFrame().getTableOccupationVoies().getModel()).fireTableDataChanged();
    }

    private void initTable() {
        for(int i = NB_VOIES; i > 0; --i) {
            inboundTrains.put(i, null);
        }
        // TODO : Table needs to be initialized to be able to set specific trains to specific platform
    }

}
