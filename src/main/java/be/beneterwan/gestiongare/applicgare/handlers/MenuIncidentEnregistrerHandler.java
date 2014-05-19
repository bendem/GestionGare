package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import serialize.ObjectSaver;

/**
 * @author bendem & Curlybear
 */
public class MenuIncidentEnregistrerHandler implements EventHandler {

    private static final Logger LOGGER = new CustomLogger(MenuIncidentEnregistrerHandler.class);
    private final ApplicGare applicGare;

    public MenuIncidentEnregistrerHandler(ApplicGare applicGare) {
        this.applicGare = applicGare;
    }

    @Override
    public void execute(EventObject event) {
        ObjectSaver objectSaver = new ObjectSaver("." + File.separator + "incidents.dat");
        try {
            objectSaver.save(applicGare.getListEvents());
        } catch(IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

}
