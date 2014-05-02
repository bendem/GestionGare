package be.beneterwan.gestiongare.applicdepot;

import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import network.NetworkStringReceiver;

/**
 * @author bendem et Curlybear
 */
public class ApplicDepotReceiver extends AbstractRunnable {

    private NetworkStringReceiver receiver;
    private final ApplicDepot applicDepot;

    public ApplicDepotReceiver(ApplicDepot applicDepot) {
        this.applicDepot = applicDepot;
    }

    @Override
    protected void startup() {
        receiver = new NetworkStringReceiver(50005);
    }

    @Override
    protected void work() {
        String message = receiver.getMessage();
        if(!"RIEN".equals(message)) {
            applicDepot.addApplicDepotMessage(message);
        }
    }

    @Override
    protected void shutdown() {
        receiver.endReceiving();
    }
}
