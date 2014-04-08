package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.applicgare.events.EventHandler;
import java.util.EventObject;

/**
 * @author bendem et Curlybear
 */
public class MenuUtilisateurLogHandler implements EventHandler {

    private final ApplicGareFrame frame;

    public MenuUtilisateurLogHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    @Override
    public void execute(EventObject event) {
        if(frame.isLoggedIn()) {
            frame.setLoggedIn(null);
        } else {
            frame.openLoginFrame();
        }
    }

}
