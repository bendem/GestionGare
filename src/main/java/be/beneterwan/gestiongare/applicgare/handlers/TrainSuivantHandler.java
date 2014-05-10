package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class TrainSuivantHandler implements EventHandler {

    private final ApplicGareFrame frame;

    public TrainSuivantHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    @Override
    public void execute(EventObject event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
