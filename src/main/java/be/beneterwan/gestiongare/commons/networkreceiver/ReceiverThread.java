package be.beneterwan.gestiongare.commons.networkreceiver;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import java.util.logging.Logger;
import network.NetworkStringReceiver;

/**
 * @author bendem et Curlybear
 */
/* package */ class ReceiverThread extends AbstractRunnable {

    private static final Logger LOGGER = new CustomLogger(ReceiverThread.class.getSimpleName());
    private NetworkStringReceiver networkStringReceiver;
    private final NetworkReceiver networkReceiver;

    /* package */  ReceiverThread(NetworkReceiver receiver) {
        this.networkReceiver = receiver;
    }

    @Override
    protected void startup() {
        networkStringReceiver = new NetworkStringReceiver(networkReceiver.getPort());
    }

    @Override
    protected void work() {
        String message = networkStringReceiver.getMessage();
        if(!"RIEN".equals(message)) {
            networkReceiver.dispatchMessage(message);
        }
    }

    @Override
    protected void shutdown() {
        try {
            networkStringReceiver.endReceiving();
        } catch(NullPointerException e) {
            LOGGER.severe("Could not stop receiving, the program will not shutdown correctly!");
        }
    }
}
