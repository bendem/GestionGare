package be.beneterwan.gestiongare.commons.eventmanagement;

import be.beneterwan.gestiongare.commons.networkreceiver.MessageEvent;
import be.beneterwan.gestiongare.commons.networkreceiver.MessageListener;
import be.beneterwan.gestiongare.commons.networkreceiver.NetworkReceiver;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.eventmanagement.EventManager;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public class NetworkEventManager extends EventManager implements MessageListener {

    private static Logger LOGGER = new CustomLogger(NetworkEventManager.class.getSimpleName());

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
