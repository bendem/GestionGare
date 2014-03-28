package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.logger.CustomLogger;
import java.net.URL;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public class ApplicGare {

    public static final Logger LOGGER = new CustomLogger(ApplicGareFrame.class.getSimpleName());

    protected static ApplicGareFrame applicGareFrame;

    public static void main(String[] args) {
        //ApplicGareFrame.main(args);
        applicGareFrame = new ApplicGareFrame();
        applicGareFrame.setVisible(true);
        applicGareFrame.setLoggedIn(null);
        applicGareFrame.openLoginFrame();
    }

    public static URL getResourceFile(String filename) {
        return ApplicGare.class.getClassLoader().getResource("resources/" + filename);
    }

}
