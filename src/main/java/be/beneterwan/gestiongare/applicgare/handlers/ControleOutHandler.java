package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.network.messages.TrainComing;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class ControleOutHandler implements EventHandler {

    private final ApplicGare applicGare;

    public ControleOutHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
    }
}
