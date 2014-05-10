package be.beneterwan.gestiongare.schedulegenerator;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.trains.HoraireComparator;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import be.beneterwan.gestiongare.commons.trains.Locomotive;
import be.beneterwan.gestiongare.commons.trains.Train;
import be.beneterwan.gestiongare.commons.trains.TrainWithoutLocomotiveException;
import be.beneterwan.gestiongare.commons.trains.Wagon;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import serialize.ObjectSaver;

/**
 * @author bendem & Curlybear
 */
public class ScheduleGenerator {

    private static final Logger LOGGER = new CustomLogger(ScheduleGenerator.class.getSimpleName());

    public static void main(String[] args) {
        LOGGER.info("Generating schedules...");

        Set<HoraireTrain> horaires = new TreeSet<>(new HoraireComparator());

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


        try {
            train = new Train(loc2, Train.Type.ICE, 123);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(10, Wagon.Type.Marchandise, 104, 2002))
            .add(new Wagon(10, Wagon.Type.Marchandise, 105, 1998));

        horaires.add(new HoraireTrain(train, "Aachen", "Ostende", 12, 50, 13, 48, 2));


        try {
            train = new Train(loc3, Train.Type.IR, 456);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(20, Wagon.Type.Marchandise, 106, 2012))
            .add(new Wagon(22, Wagon.Type.Voyageur, 107, 2004));

        horaires.add(new HoraireTrain(train, "Liège-G", "Maastricht", 18, 47, 19, 56, 3));

        try {
            train = new Train(loc4, Train.Type.L, 789);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(15, Wagon.Type.Marchandise, 108, 2014))
            .add(new Wagon(15, Wagon.Type.Marchandise, 109, 2001));

        horaires.add(new HoraireTrain(train, "Liège-G", "Vise", 2, 14, 3, 01, 4));

        try {
            train = new Train(loc1, Train.Type.Thalis, 159);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(26, Wagon.Type.Voyageur, 102, 2009))
            .add(new Wagon(26, Wagon.Type.Voyageur, 103, 1999));

        horaires.add(new HoraireTrain(train, "bruxelles-midi", "Lille", 10, 40, 11, 02, 5));

        ObjectSaver saver = new ObjectSaver("./schedules.dat");
        try {
            saver.save(horaires);
        } catch(IOException ex) {
            LOGGER.log(Level.SEVERE, "Could not save schedules", ex);
        }

        LOGGER.info("Création des trains terminée");
    }

}
