package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.applicgare.handlers.MenuAideAboutHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuAideDateHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuTrainListHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuUtilisateurAddHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuUtilisateurListHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuUtilisateurLogHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MessageDepotHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MessagePostesInHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MessagePostesOutHandler;
import be.beneterwan.gestiongare.applicgare.handlers.TrainSuivantHandler;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.network.receiver.NetworkReceiver;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import serialize.ObjectLoader;

/**
 * @author bendem & Curlybear
 */
public class ApplicGare {

    private static final Logger LOGGER = new CustomLogger(ApplicGareFrame.class.getSimpleName());
    private static ApplicGare instance;
    private static ApplicGareFrame frame;

    private final ApplicGareEventManager eventManager;
    private final NetworkReceiver postesInNetworkReceiver;
    private final NetworkReceiver postesOutNetworkReceiver;
    private final NetworkReceiver depotNetworkReceiver;
    private Set<HoraireTrain> horaires;

    public ApplicGare() {
        System.out.println("\n  #######################################");
        System.out.println("  #   Gestion Gare : Application Gare   #");
        System.out.println("  #######################################\n");
        LOGGER.info("Starting up application...");

        // Loading ui
        frame = new ApplicGareFrame(this);
        frame.setVisible(true);
        frame.setLoggedIn(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                stopThreads();
                frame.dispose();
            }
        });

        // Setting up events
        eventManager = new ApplicGareEventManager();
        eventManager.addListener(frame.getMenuUtilisateurLog(), new MenuUtilisateurLogHandler(frame));
        eventManager.addListener(frame.getMenuAideAbout(), new MenuAideAboutHandler(frame));
        eventManager.addListener(frame.getMenuAideDate(), new MenuAideDateHandler(frame));
        eventManager.addListener(frame.getMenuUtilisateurListe(), new MenuUtilisateurListHandler(frame));
        eventManager.addListener(frame.getMenuUtilisateurNouvelUtilisateur(), new MenuUtilisateurAddHandler(frame));
        eventManager.addListener(frame.getMenuTrainListe(), new MenuTrainListHandler(frame));
        eventManager.addListener(frame.getButtonTrainSuivant(), new TrainSuivantHandler(frame));

        // Opening login frame
        frame.openLoginFrame();

        // Preparing utilities
        postesInNetworkReceiver = new NetworkReceiver(50_010);
        postesOutNetworkReceiver = new NetworkReceiver(50_011);
        depotNetworkReceiver = new NetworkReceiver(50_015);

        eventManager.addListener(postesInNetworkReceiver, new MessagePostesInHandler(this));
        eventManager.addListener(postesOutNetworkReceiver, new MessagePostesOutHandler(this));
        eventManager.addListener(depotNetworkReceiver, new MessageDepotHandler(this));

        try {
            // Loading train list
            LOGGER.info("Loading schedule...");
            horaires = (Set<HoraireTrain>) new ObjectLoader("./schedules.dat").load();
        } catch(IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Could not load trains schedule, exiting...", ex);
            System.exit(0);
        }
    }

    public void startThreads() {
        LOGGER.info("Starting threads");
        if(!postesInNetworkReceiver.isRunning()) {
            postesInNetworkReceiver.start();
        }
        if(!postesOutNetworkReceiver.isRunning()) {
            postesOutNetworkReceiver.start();
        }
        if(!depotNetworkReceiver.isRunning()) {
            depotNetworkReceiver.start();
        }
    }

    public void stopThreads() {
        LOGGER.info("Stopping threads");
        if(postesInNetworkReceiver.isRunning()) {
            postesInNetworkReceiver.stop();
        }
        if(postesOutNetworkReceiver.isRunning()) {
            postesOutNetworkReceiver.stop();
        }
        if(depotNetworkReceiver.isRunning()) {
            depotNetworkReceiver.stop();
        }
        LOGGER.fine("Thread stopped");
    }

    public Set<HoraireTrain> getHoraires() {
        return horaires;
    }

    public ApplicGareEventManager getEventManager() {
        return eventManager;
    }

    public static void main(String[] args) {
        instance = new ApplicGare();
    }

}
