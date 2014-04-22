package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public class ApplicGare {

    private static final Logger LOGGER = new CustomLogger(ApplicGareFrame.class.getSimpleName());
    private static ApplicGare instance;
    private static ApplicGareFrame applicGareFrame;

    private final AbstractRunnable applicDepotReceiver;
    private final Queue<String> applicDepotMessages;

    public ApplicGare() {
        System.out.println("\n  #######################################");
        System.out.println("  #   Gestion Gare : Application Gare   #");
        System.out.println("  #######################################\n");
        LOGGER.info("Starting up application...");
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
        applicDepotMessages = new ConcurrentLinkedQueue<>();
        applicDepotReceiver = new ApplicDepotReceiver(this);
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
        applicDepotReceiver.cancel();
    }

    public static void main(String[] args) {
        instance = new ApplicGare();
    }

}
