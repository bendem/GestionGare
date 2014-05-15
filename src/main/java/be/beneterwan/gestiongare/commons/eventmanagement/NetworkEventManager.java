package be.beneterwan.gestiongare.commons.eventmanagement;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.network.receiver.MessageEvent;
import be.beneterwan.gestiongare.commons.network.receiver.MessageListener;
import be.beneterwan.gestiongare.commons.network.receiver.NetworkReceiver;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class NetworkEventManager extends EventManager implements MessageListener {

    private static final Logger LOGGER = new CustomLogger(NetworkEventManager.class);

    public void addListener(NetworkReceiver receiver, EventHandler hanlder) {
        receiver.addMessageListener(this);
        registerHandler(receiver, hanlder);
    }

    @Override
    public void onMessage(MessageEvent event) {
        LOGGER.fine("Message received : " + event.getMessage());
        dispatchEvent(event);
    }

}
