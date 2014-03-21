package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.logger.CustomLogger;
import java.net.URL;
import java.util.logging.Logger;

/**
 * @author bendem
 */
public class ApplicGare {

    public static final Logger LOGGER = new CustomLogger(ApplicGareFrame.class.getSimpleName());

    public static void main(String[] args) {
        ApplicGareFrame.main(args);
        //new ApplicGareFrame();
    }

    public static URL getResourceFile(String filename) {
        return ApplicGare.class.getClassLoader().getResource("resources/" + filename);
    }

}
