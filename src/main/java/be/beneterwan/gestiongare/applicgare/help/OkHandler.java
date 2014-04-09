package be.beneterwan.gestiongare.applicgare.help;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.applicgare.DateFormat;
import be.beneterwan.gestiongare.applicgare.events.EventHandler;
import java.util.EventObject;

/**
 * @author bendem et Curlybear
 */
public class OkHandler implements EventHandler {

    private final DateFormatDialog dialog;

    public OkHandler(DateFormatDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void execute(EventObject event) {
        ApplicGareFrame parent = (ApplicGareFrame) dialog.getParent();
        parent.setDateFormat(dialog.getDateFormat());
        dialog.dispose();
    }
}
