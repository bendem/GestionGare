package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.applicgare.handlers.ControleInHandler;
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
import java.util.Set;
import java.util.logging.Logger;
import network.NetworkStringSender;

/**
 * @author bendem & Curlybear
 */
public class ApplicGare {

    private static final Logger LOGGER = new CustomLogger(ApplicGareFrame.class.getSimpleName());
    private static ApplicGare instance;
    private static ApplicGareFrame frame;

    private final TrainManager trainManager;
    private final ApplicGareEventManager eventManager;
    private final NetworkReceiver postesInNetworkReceiver;
    private final NetworkReceiver postesOutNetworkReceiver;
    private final NetworkReceiver depotNetworkReceiver;
    private NetworkStringSender postesInNetworkSender;
    private NetworkStringSender postesOutNetworkSender;
    private NetworkStringSender depotNetworkSender;

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
                stopUtilities();
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
        eventManager.addListener(frame.getButtonTrainSuivant(), new TrainSuivantHandler(this));
        eventManager.addListener(frame.getButtonControleIn(), new ControleInHandler(this));

        // Opening login frame
        frame.openLoginFrame();

        // Preparing utilities
        postesInNetworkReceiver = new NetworkReceiver(50_010);
        postesOutNetworkReceiver = new NetworkReceiver(50_011);
        depotNetworkReceiver = new NetworkReceiver(50_015);

        postesInNetworkReceiver.start();
        postesOutNetworkReceiver.start();
        depotNetworkReceiver.start();

        eventManager.addListener(postesInNetworkReceiver, new MessagePostesInHandler(this));
        eventManager.addListener(postesOutNetworkReceiver, new MessagePostesOutHandler(this));
        eventManager.addListener(depotNetworkReceiver, new MessageDepotHandler(this));

        trainManager = new TrainManager();
    }

    public void startUtilities() {
        LOGGER.info("Starting utilities...");

        postesInNetworkSender = new NetworkStringSender("127.0.0.1", 50_000);
        postesOutNetworkSender = new NetworkStringSender("127.0.0.1", 50_001);
        depotNetworkSender = new NetworkStringSender("127.0.0.1", 50_005);
    }

    public void stopUtilities() {
        LOGGER.info("Stopping utilities...");

        if(postesInNetworkReceiver.isRunning()) {
            postesInNetworkReceiver.stop();
        }
        if(postesOutNetworkReceiver.isRunning()) {
            postesOutNetworkReceiver.stop();
        }
        if(depotNetworkReceiver.isRunning()) {
            depotNetworkReceiver.stop();
        }

        postesInNetworkSender.endSending();
        postesOutNetworkSender.endSending();
        depotNetworkSender.endSending();
        LOGGER.fine("Utilities stopped");
    }

    public Set<HoraireTrain> getHoraires() {
        return horaires;
    }

    public ApplicGareEventManager getEventManager() {
        return eventManager;
    }

    public ApplicGareFrame getFrame() {
        return frame;
    }

    public TrainManager getTrainManager() {
        return trainManager;
    }

    public NetworkStringSender getPostesInNetworkSender() {
        return postesInNetworkSender;
    }

    public NetworkStringSender getPostesOutNetworkSender() {
        return postesOutNetworkSender;
    }

    public NetworkStringSender getDepotNetworkSender() {
        return depotNetworkSender;
    }

    public static void main(String[] args) {
        instance = new ApplicGare();
    }

}
