package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.trains.HoraireComparator;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain.State;
import be.beneterwan.gestiongare.commons.trains.Train;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import serialize.ObjectLoader;
import serialize.ObjectSaver;

/**
 * @author bendem & Curlybear
 */
public class TrainManager {

    private static final Logger LOGGER = new CustomLogger(TrainManager.class);

    public static final int NB_VOIES = 8;

    private final Queue<HoraireTrain> incomingTrains;
    private final Map<Integer, HoraireTrain> inboundTrains;
    private final LinkedList<HoraireTrain> outboundTrains;
    private final ApplicGare applicGare;
    private HoraireTrain newCurrent;
    private HoraireTrain storeCurrent;
    private HoraireTrain outCurrent;

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

    private void initTable() {
        for(int i = NB_VOIES; i > 0; --i) {
            inboundTrains.put(i, null);
        }
    }

    public HoraireTrain nextTrain() {
        newCurrent = incomingTrains.poll();
        return newCurrent;
    }

    public void setCurrentTrainInbound() {
        if(newCurrent.getState() != null) {
            throw new IllegalStateException("Can't set train as coming if it already came!");
        }
        if(inboundTrains.get(newCurrent.getQuai()) != null) {
            throw new IllegalStateException("Can't add train to an occupied platform!");
        }
        newCurrent.setState(State.Inbound);
        inboundTrains.put(newCurrent.getQuai(), newCurrent);
        updateStationedTrains();
    }

    public void trainArrived(HoraireTrain horaire) {
        if(horaire.getState() != State.Inbound) {
            throw new IllegalStateException("Can't station train which was not coming!");
        }
        if(inboundTrains.get(horaire.getQuai()) == null) {
            throw new IllegalStateException("Can't update a train which was not displayed!");
        }
        horaire.setState(State.Stationned);
        inboundTrains.put(horaire.getQuai(), horaire);
        updateStationedTrains();
    }

    public void trainLeaving(HoraireTrain horaire) {
        if(horaire.getState() != State.Stationned) {
            throw new IllegalStateException("Can't set train as `Leaving` if it was not `Stationned`!");
        }
        if(inboundTrains.get(horaire.getQuai()) == null) {
            throw new IllegalStateException("Can't update a train which was not displayed!");
        }
        horaire.setState(State.Leaving);
        inboundTrains.put(horaire.getQuai(), horaire);
        updateStationedTrains();
    }

    public void trainLeft(HoraireTrain horaire) {
        if(inboundTrains.get(horaire.getQuai()) == null) {
            throw new IllegalStateException("Can't remove a train which was not displayed!");
        }
        if(outboundTrains.contains(horaire)) {
            throw new IllegalStateException("Can't mark a train as gone if it already was!");
        }
        inboundTrains.put(horaire.getQuai(), null);
        outboundTrains.add(horaire);
        saveOutboundTrains();
        updateStationedTrains();
        ((TrainGoneComboBoxModel) applicGare.getFrame().getComboBoxTrain().getModel()).update();
    }

    public HoraireTrain getCurrent() {
        return newCurrent;
    }

    public Map<Integer, HoraireTrain> getInboundTrains() {
        return inboundTrains;
    }

    public HoraireTrain getStoreCurrent() {
        return storeCurrent;
    }

    public void setStoreCurrent(HoraireTrain storeCurrent) {
        this.storeCurrent = storeCurrent;
    }

    public HoraireTrain getOutCurrent() {
        return outCurrent;
    }

    public void setOutCurrent(HoraireTrain outCurrent) {
        this.outCurrent = outCurrent;
    }

    public void storeCurrent() {
        this.storeCurrent = this.newCurrent;
    }

    public String getStoreCurrentNum() {
        return storeCurrent.getTrain().toString();
    }

    private void saveOutboundTrains() {
        ObjectSaver objectSaver = new ObjectSaver("train_gone.dat");
        List<Train> trains = new LinkedList<>();
        for(HoraireTrain horaire : outboundTrains) {
            trains.add(horaire.getTrain());
        }
        try {
            objectSaver.save(trains);
        } catch(IOException ex) {
            LOGGER.log(Level.SEVERE, "Could not save trains", ex);
        }
    }

    private void updateStationedTrains() {
        ((OccupationVoiesTableModel) applicGare.getFrame().getTableOccupationVoies().getModel()).fireTableDataChanged();
    }

    public List<HoraireTrain> getOutboundTrains() {
        return outboundTrains;
    }

    public Set<HoraireTrain> getAllTrains() {
        Set<HoraireTrain> set = new TreeSet<>(new HoraireComparator());

        set.addAll(incomingTrains);
        set.addAll(inboundTrains.values());
        set.addAll(outboundTrains);
        set.add(newCurrent);
        set.add(outCurrent);

        return set;
    }

}
