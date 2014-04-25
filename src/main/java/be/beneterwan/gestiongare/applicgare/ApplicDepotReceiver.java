package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import java.util.logging.Logger;
import network.NetworkStringReceiver;

/**
 * @author bendem et Curlybear
 */
public class ApplicDepotReceiver extends AbstractRunnable {

    private static final Logger LOGGER = new CustomLogger(AbstractRunnable.class.getSimpleName());

    private final ApplicGare applicGare;
    private NetworkStringReceiver receiver;

    public ApplicDepotReceiver(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    protected void startup() {
        receiver = new NetworkStringReceiver(1500);
    }

    @Override
    protected void work() {
        String message = receiver.getMessage();
        if(!"RIEN".equals(message)) {
            applicGare.addApplicDepotMessage(message);
        }
    }

    @Override
    protected void shutdown() {
        try {
            receiver.endReceiving();
        } catch(NullPointerException e) {
            LOGGER.severe("Could not stop receiving, the program will not shutdown correctly!");
        }
    }

}
