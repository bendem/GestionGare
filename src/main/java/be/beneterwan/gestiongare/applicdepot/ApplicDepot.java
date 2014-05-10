package be.beneterwan.gestiongare.applicdepot;

import be.beneterwan.gestiongare.applicdepot.handlers.MessageHandler;
import be.beneterwan.gestiongare.commons.eventmanagement.NetworkEventManager;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.network.receiver.NetworkReceiver;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class ApplicDepot {

    private static final Logger LOGGER = new CustomLogger(ApplicDepotFrame.class.getSimpleName());
    protected static ApplicDepotFrame applicDepotFrame;
    private static ApplicDepot instance;
    private final NetworkReceiver networkReceiver;
    private final NetworkEventManager eventManager;

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
        eventManager = new NetworkEventManager();
        networkReceiver = new NetworkReceiver();

        networkReceiver.setPort(50005);
        eventManager.addListener(networkReceiver, new MessageHandler(this));

        startThreads();
    }

    public void startThreads() {
        LOGGER.info("Starting threads");
        if(!networkReceiver.isRunning()) {
            networkReceiver.start();
        }
    }

    public void stopThreads() {
        LOGGER.info("Stopping threads");
        if(networkReceiver.isRunning()) {
            networkReceiver.stop();
        }
    }

    public ApplicDepotFrame getFrame() {
        return applicDepotFrame;
    }

    public static void main(String[] args) {
        instance = new ApplicDepot();
    }

}
