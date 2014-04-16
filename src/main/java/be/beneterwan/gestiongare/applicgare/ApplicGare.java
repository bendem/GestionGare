package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
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
        applicDepotMessages = new ConcurrentLinkedQueue<>();
    }

    void addApplicDepotMessage(String message) {
        applicDepotMessages.add(message);
    }

    public static void main(String[] args) {
        instance = new ApplicGare();
    }

}
