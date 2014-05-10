package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class MenuTrainListHandler implements EventHandler {

    private final ApplicGareFrame frame;

    public MenuTrainListHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    @Override
    public void execute(EventObject event) {
        frame.openTrainListDialog();
    }

}
