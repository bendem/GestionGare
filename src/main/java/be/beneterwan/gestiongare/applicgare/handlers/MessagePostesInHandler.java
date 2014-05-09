package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 *
 * @author Bear
 */
public class MessagePostesInHandler implements EventHandler{

    private final ApplicGare applicGare;

    public MessagePostesInHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
    }

}
