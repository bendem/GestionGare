package be.beneterwan.gestiongare.applicgare.dialogs.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class IncidentDialogResoudreHandler implements EventHandler {

    private final ApplicGareFrame frame;

    public IncidentDialogResoudreHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    @Override
    public void execute(EventObject event) {
        frame.getApplicGare().shitIsBetter();
    }

}
