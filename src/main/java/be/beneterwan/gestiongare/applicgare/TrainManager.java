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

    private static final Logger LOGGER = new CustomLogger(ApplicGareFrame.class);

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

    // TODO Fucking load of state check and exceptions

    public HoraireTrain nextTrain() {
        newCurrent = incomingTrains.poll();
        return newCurrent;
    }

    public void setCurrentTrainInbound() {
        newCurrent.setState(State.Inbound);
        inboundTrains.put(newCurrent.getQuai(), newCurrent);
        updateStationedTrains();
    }

    public void trainArrived(HoraireTrain horaire) {
        horaire.setState(State.Stationned);
        inboundTrains.put(horaire.getQuai(), horaire);
        updateStationedTrains();
    }

    public void trainLeaving(HoraireTrain horaire) {
        horaire.setState(State.Leaving);
        inboundTrains.put(horaire.getQuai(), horaire);
        updateStationedTrains();
    }

    public void trainLeft(HoraireTrain horaire) {
        inboundTrains.put(horaire.getQuai(),null);
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

    public void storeCurrent(){
        this.storeCurrent = this.newCurrent;
    }

    public String getStoreCurrentNum(){
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
