package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import serialize.ObjectLoader;

/**
 * @author bendem et Curlybear
 */
public class ApplicGare {

    private static final Logger LOGGER = new CustomLogger(ApplicGareFrame.class.getSimpleName());
    private static ApplicGare instance;
    private static ApplicGareFrame applicGareFrame;

    private final AbstractRunnable applicDepotReceiver;
    private final Queue<String> applicDepotMessages;
    private Set<HoraireTrain> horaires;

    public ApplicGare() {
        System.out.println("\n  #######################################");
        System.out.println("  #   Gestion Gare : Application Gare   #");
        System.out.println("  #######################################\n");
        LOGGER.info("Starting up application...");
        // Loading interface
        applicGareFrame = new ApplicGareFrame(this);
        applicGareFrame.setVisible(true);
        applicGareFrame.setLoggedIn(null);
        applicGareFrame.openLoginFrame();
        applicGareFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                stopThreads();
                applicGareFrame.dispose();
            }
        });

        // Starting utilities
        applicDepotMessages = new ConcurrentLinkedQueue<>();
        applicDepotReceiver = new ApplicDepotReceiver(this);

        try {
            // Loading train list
            horaires = (Set<HoraireTrain>) new ObjectLoader("./schedules.dat").load();
        } catch(IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Could not load trains schedule, exiting...", ex);
            System.exit(0);
        }
    }

    public Set<HoraireTrain> getHoraires() {
        return horaires;
    }

    void addApplicDepotMessage(String message) {
        applicDepotMessages.add(message);
    }

    public void startThreads() {
        LOGGER.info("Starting threads");
        applicDepotReceiver.start();
    }

    public void stopThreads() {
        LOGGER.info("Stopping threads");
        if(applicDepotReceiver.isRunning()) {
            applicDepotReceiver.cancel();
        }
        LOGGER.fine("Thread stopped");
    }

    public static void main(String[] args) {
        instance = new ApplicGare();
    }

}
