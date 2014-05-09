package be.beneterwan.gestiongare.applicpostes;

import be.beneterwan.gestiongare.applicgare.ApplicDepotReceiver;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import java.util.logging.Logger;
import network.NetworkStringReceiver;
/**
 *
 * @author Bear
 */
public class ApplicGareReceiver extends AbstractRunnable {
    
    private static final Logger LOGGER = new CustomLogger(ApplicDepotReceiver.class.getSimpleName());
    private NetworkStringReceiver receiver;
    private ApplicPostes applicPostes;

    public ApplicGareReceiver(ApplicPostes applicPostes) {
        this.applicPostes = applicPostes;
    }

    @Override
    protected void startup() {
        if(applicPostes.getType().equals(ApplicPostes.Type.In))
            receiver = new NetworkStringReceiver(50000);
        else
            receiver = new NetworkStringReceiver(50001);
    }

    @Override
    protected void work() {
        String message = receiver.getMessage();
        if(!"RIEN".equals(message)) {
            applicPostes.addApplicPostesMessage(message);
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
