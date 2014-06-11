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

    private static final Logger LOGGER = new CustomLogger(ScheduleGenerator.class);

    public static void main(String[] args) {
        LOGGER.info("Generating schedules...");

        Set<HoraireTrain> horaires = new TreeSet<>(new HoraireComparator());

        Locomotive loc1 = new Locomotive(4500, 501, 1990);
        Locomotive loc2 = new Locomotive(5400, 502, 1989);
        Locomotive loc3 = new Locomotive(4300, 503, 1985);
        Locomotive loc4 = new Locomotive(5401, 504, 1994);
        Locomotive loc5 = new Locomotive(5402, 505, 1994);
        Locomotive loc6 = new Locomotive(5403, 506, 1994);
        Locomotive loc7 = new Locomotive(5404, 507, 2001);
        Locomotive loc8 = new Locomotive(5405, 508, 1994);
        Locomotive loc9 = new Locomotive(5406, 509, 1995);

        Train train = null;
        try {
            train = new Train(loc1, Train.Type.IC, 420);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(271, Wagon.Type.Voyageur, 102, 2009))
            .add(new Wagon(270, Wagon.Type.Voyageur, 102, 2009))
            .add(new Wagon(212, Wagon.Type.Voyageur, 102, 2009))
            .add(new Wagon(280, Wagon.Type.Voyageur, 103, 1999));

        horaires.add(new HoraireTrain(train, "bruxelles-midi", "eupen", 9, 33, 9, 36, 1));


        try {
            train = new Train(loc2, Train.Type.ICE, 123);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(11, Wagon.Type.Marchandise, 104, 2002))
            .add(new Wagon(10, Wagon.Type.Marchandise, 105, 1998));

        horaires.add(new HoraireTrain(train, "Aachen", "Ostende", 12, 50, 12, 59, 2));


        try {
            train = new Train(loc3, Train.Type.IR, 456);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(20, Wagon.Type.Marchandise, 106, 2012))
            .add(new Wagon(22, Wagon.Type.Voyageur, 107, 2004));

        horaires.add(new HoraireTrain(train, "Liège-G", "Maastricht", 19, 47, 19, 56, 3));

        try {
            train = new Train(loc4, Train.Type.L, 789);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(15, Wagon.Type.Marchandise, 108, 2014))
            .add(new Wagon(16, Wagon.Type.Marchandise, 109, 2001));

        horaires.add(new HoraireTrain(train, "Liège-G", "Vise", 2, 14, 2, 10, 4));

        try {
            train = new Train(loc5, Train.Type.Thalis, 159);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(27, Wagon.Type.Voyageur, 102, 2009))
            .add(new Wagon(28, Wagon.Type.Voyageur, 103, 1999));

        horaires.add(new HoraireTrain(train, "bruxelles-midi", "Lille", 11, 02, 11, 20, 5));

        try {
            train = new Train(loc6, Train.Type.IC, 160);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(42, Wagon.Type.Voyageur, 102, 2009))
            .add(new Wagon(84, Wagon.Type.Voyageur, 103, 1999));

        horaires.add(new HoraireTrain(train, "Bruxelles-Nord", "Eupen", 11, 0, 11, 05, 6));

        try {
            train = new Train(loc7, Train.Type.IC, 161);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(126, Wagon.Type.Voyageur, 104, 2009))
            .add(new Wagon(215, Wagon.Type.Voyageur, 105, 1999));

        horaires.add(new HoraireTrain(train, "Paris-Nord", "Liège-Guillemins", 12, 0, 12, 03, 4));

        try {
            train = new Train(loc8, Train.Type.IC, 162);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(26, Wagon.Type.Voyageur, 106, 2009))
            .add(new Wagon(26, Wagon.Type.Voyageur, 107, 1999));

        horaires.add(new HoraireTrain(train, "Bruxelles-Nord", "Eupen", 11, 1, 12, 0, 3));

        try {
            train = new Train(loc6, Train.Type.IC, 160);
        } catch(TrainWithoutLocomotiveException ex) {
            LOGGER.log(Level.SEVERE, "Y U NO LOCO?!?", ex);
        }

        train
            .add(new Wagon(26, Wagon.Type.Voyageur, 108, 2009))
            .add(new Wagon(26, Wagon.Type.Voyageur, 109, 1999));

        horaires.add(new HoraireTrain(train, "Bruxelles-Nord", "Eupen", 11, 2, 11, 15, 2));

        ObjectSaver saver = new ObjectSaver("./schedules.dat");
        try {
            saver.save(horaires);
        } catch(IOException ex) {
            LOGGER.log(Level.SEVERE, "Could not save schedules", ex);
        }

        LOGGER.info("Schedule generated");
    }

}
