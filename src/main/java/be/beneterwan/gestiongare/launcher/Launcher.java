package be.beneterwan.gestiongare.launcher;

import be.beneterwan.gestiongare.applicdepot.ApplicDepot;
import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.applicpostes.ApplicPostes;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class Launcher {

    private static final Logger LOGGER = new CustomLogger(Launcher.class.getSimpleName());

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ApplicStarter(ApplicGare.class)).start();
        new Thread(new ApplicStarter(ApplicPostes.class)).start();
        new Thread(new ApplicStarter(ApplicPostes.class)).start();
        new Thread(new ApplicStarter(ApplicDepot.class)).start();
    }
}
