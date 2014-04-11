package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem et Curlybear
 */
public class MenuUtilisateurListHandler implements EventHandler {

    private final ApplicGareFrame frame;

    public MenuUtilisateurListHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    @Override
    public void execute(EventObject event) {
        frame.openListUserDialog();
    }

}
