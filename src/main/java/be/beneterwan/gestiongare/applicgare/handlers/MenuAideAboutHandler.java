package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.applicgare.events.EventHandler;
import java.util.EventObject;

/**
 * @author bendem et Curlybear
 */
public class MenuAideAboutHandler implements EventHandler {

    private final ApplicGareFrame frame;

    public MenuAideAboutHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    @Override
    public void execute(EventObject event) {
        frame.openAboutFrame();
    }

}
