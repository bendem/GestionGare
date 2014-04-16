package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import java.util.logging.Logger;
import network.NetworkStringReceiver;

/**
 * @author bendem et Curlybear
 */
public class ApplicDepotReceiver extends AbstractRunnable {

    private static final Logger LOGGER = new CustomLogger(ApplicDepotReceiver.class.getSimpleName());

    private final NetworkStringReceiver receiver;
    private final ApplicGare applicGare;

    public ApplicDepotReceiver(ApplicGare applicGare) {
        this.applicGare = applicGare;
        receiver = new NetworkStringReceiver(1500);
    }

    @Override
    protected void work() {
        String message = receiver.getMessage();
        if(!"RIEN".equals(message)) {
            applicGare.addApplicDepotMessage(message);
        }
    }

}
