package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.Tools;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.util.logging.Logger;
import network.NetworkStringReceiver;

/**
 * @author bendem et Curlybear
 */
public class ApplicDepotReceiver implements Runnable {

    private static final Logger LOGGER = new CustomLogger(ApplicDepotReceiver.class.getSimpleName());

    private final NetworkStringReceiver receiver;
    private final ApplicGare applicGare;
    private boolean cancelled;

    public ApplicDepotReceiver(ApplicGare applicGare) {
        this.applicGare = applicGare;
        receiver = new NetworkStringReceiver(1500);
        cancelled = false;
    }

    @Override
    public void run() {
        while(!cancelled) {
            String message = receiver.getMessage();
            if(!"RIEN".equals(message)) {
                applicGare.addApplicDepotMessage(message);
            }
            Tools.pause(100);
        }
    }

    public void cancel() {
        this.cancelled = true;
    }

}
