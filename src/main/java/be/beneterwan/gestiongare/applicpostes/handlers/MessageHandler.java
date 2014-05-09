package be.beneterwan.gestiongare.applicpostes.handlers;

import be.beneterwan.gestiongare.applicpostes.ApplicPostes;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem et Curlybear
 */
public class MessageHandler implements EventHandler {

    private final ApplicPostes applicPostes;

    public MessageHandler(ApplicPostes applicPostes) {
        this.applicPostes = applicPostes;
    }

    @Override
    public void execute(EventObject event) {
    }

}
