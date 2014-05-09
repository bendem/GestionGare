package be.beneterwan.gestiongare.applicpostes.receiver;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.threads.AbstractRunnable;
import java.util.logging.Logger;
import network.NetworkStringReceiver;
/**
 *
 * @author Bear
 */
/* package */ class ReceiverThread extends AbstractRunnable {

    private static final Logger LOGGER = new CustomLogger(ReceiverThread.class.getSimpleName());
    private NetworkStringReceiver networkReceiver;
    private final Receiver receiver;

    /* package */  ReceiverThread(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    protected void startup() {
        networkReceiver = new NetworkStringReceiver(receiver.getPort());
    }

    @Override
    protected void work() {
        String message = networkReceiver.getMessage();
        if(!"RIEN".equals(message)) {
            receiver.dispatchMessage(message);
        }
    }

    @Override
    protected void shutdown() {
        try {
            networkReceiver.endReceiving();
        } catch(NullPointerException e) {
            LOGGER.severe("Could not stop receiving, the program will not shutdown correctly!");
        }
    }
}
