package be.beneterwan.gestiongare.applicdepot;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public class ApplicDepot {
    
    private static final Logger LOGGER = new CustomLogger(ApplicDepotFrame.class.getSimpleName());
    protected static ApplicDepotFrame applicDepotFrame;
    private static ApplicDepot instance;
    private static final ApplicDepotReceiver receiver = new ApplicDepotReceiver();
    
    public ApplicDepot() {
        System.out.println("\n  ########################################");
        System.out.println("  #   Gestion Gare : Application Depot   #");
        System.out.println("  ########################################\n");
        LOGGER.info("Starting up application...");
        applicDepotFrame = new ApplicDepotFrame();
        applicDepotFrame.setVisible(true);
        applicDepotFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                stopThreads();
                applicDepotFrame.dispose();
            }
        });
        startThreads();
    }
      
    public void startThreads() {
        LOGGER.info("Starting threads");
        LOGGER.info("-Starting up receive thread...");
        new Thread(receiver).start();
    }

    public void stopThreads() {
        LOGGER.info("Stopping threads");
        LOGGER.info("-Stopping receive thread...");
        receiver.cancel();
    }

    
    public static void main(String[] args) {
        instance = new ApplicDepot();
    }

}
