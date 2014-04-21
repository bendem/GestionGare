package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
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
    private final List<Thread> threads;

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
        threads = new ArrayList<>();
    }

    void addApplicDepotMessage(String message) {
        applicDepotMessages.add(message);
    }

    public void startThreads() {
        LOGGER.info("Starting threads");
        threads.add(new Thread(applicDepotReceiver));
        for(Thread thread : threads) {
            thread.start();
        }
    }

    public void stopThreads() {
        LOGGER.info("Stopping threads");
        applicDepotReceiver.cancel();
        for(Thread thread : threads) {
            try {
                thread.join(500); // if it takes more than .5 sec, the thread has a problem...
            } catch(InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        instance = new ApplicGare();
    }

}
