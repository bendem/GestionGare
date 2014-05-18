package be.beneterwan.gestiongare.applicgare.dialogs.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.applicgare.dialogs.IncidentDialog;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class IncidentDialogResoudreHandler implements EventHandler {

    private final IncidentDialog dialog;

    public IncidentDialogResoudreHandler(IncidentDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void execute(EventObject event) {
        ((ApplicGareFrame) dialog.getParent()).getApplicGare().shitIsBetter();
        dialog.dispose();
    }

}
