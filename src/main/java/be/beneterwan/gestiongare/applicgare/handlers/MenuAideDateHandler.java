package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.applicgare.dialogs.DateFormatDialog;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class MenuAideDateHandler implements EventHandler {

    private final ApplicGareFrame frame;

    public MenuAideDateHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    @Override
    public void execute(EventObject event) {
        frame.openDialog(DateFormatDialog.class, frame);
    }

}
