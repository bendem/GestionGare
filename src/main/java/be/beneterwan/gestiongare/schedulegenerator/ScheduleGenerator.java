package be.beneterwan.gestiongare.schedulegenerator;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import be.beneterwan.gestiongare.commons.trains.Locomotive;
import be.beneterwan.gestiongare.commons.trains.Train;
import be.beneterwan.gestiongare.commons.trains.TrainWithoutLocomotiveException;
import be.beneterwan.gestiongare.commons.trains.Wagon;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import serialize.ObjectSaver;

/**
 * @author bendem et Curlybear
 */
public class ScheduleGenerator {

    private static final Logger LOGGER = new CustomLogger(ScheduleGenerator.class.getSimpleName());

    public static void main(String[] args) {
        LOGGER.info("Generating schedules...");

        Set<HoraireTrain> horaires = new HashSet<>();

        Locomotive loc1 = new Locomotive(4500, 501, 1990);
        Locomotive loc2 = new Locomotive(5400, 502, 1989);
        Locomotive loc3 = new Locomotive(4300, 503, 1985);
        Locomotive loc4 = new Locomotive(5400, 504, 1994);

        Train train = null;
        try {
            train = new Train(loc1, Train.Type.IC, 420);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(26, Wagon.Type.Voyageur, 102, 2009))
            .add(new Wagon(26, Wagon.Type.Voyageur, 103, 1999));

        horaires.add(new HoraireTrain(train, "bruxelles-midi", "eupen", 9, 33, 9, 36, 1));

        ObjectSaver saver = new ObjectSaver("./schedules.dat");
        try {
            saver.save(horaires);
        } catch(IOException ex) {
            LOGGER.log(Level.SEVERE, "Could not save schedules", ex);
        }
    }

}
