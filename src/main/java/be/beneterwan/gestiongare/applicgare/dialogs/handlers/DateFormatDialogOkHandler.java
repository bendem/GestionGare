package be.beneterwan.gestiongare.applicgare.dialogs.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.applicgare.dialogs.DateFormatDialog;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class DateFormatDialogOkHandler implements EventHandler {

    private final DateFormatDialog dialog;

    public DateFormatDialogOkHandler(DateFormatDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void execute(EventObject event) {
        ApplicGareFrame parent = (ApplicGareFrame) dialog.getParent();
        parent.setDateFormat(dialog.getDateFormat());
        dialog.dispose();
    }

}
