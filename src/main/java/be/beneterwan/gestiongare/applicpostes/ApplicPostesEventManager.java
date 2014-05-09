package be.beneterwan.gestiongare.applicpostes;

import be.beneterwan.gestiongare.applicpostes.receiver.MessageEvent;
import be.beneterwan.gestiongare.applicpostes.receiver.MessageListener;
import be.beneterwan.gestiongare.applicpostes.receiver.Receiver;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.eventmanagement.EventManager;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public class ApplicPostesEventManager extends EventManager implements MessageListener {

    private static Logger LOGGER = new CustomLogger(ApplicPostesEventManager.class.getSimpleName());

    public void addListener(Receiver receiver, EventHandler hanlder) {
        receiver.addMessageListener(this);
        registerHandler(receiver, hanlder);
    }

    @Override
    public void onMessage(MessageEvent event) {
        LOGGER.fine("Message received : " + event.getMessage());
        dispatchEvent(event);
    }

}
