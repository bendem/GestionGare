package be.beneterwan.gestiongare.commons.properties;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class Proprietes {

    public static final Logger LOGGER = new CustomLogger(Proprietes.class);
    private final Properties prop = new Properties();

    private String getNomFichierProperties() {
        String nomFichier = System.getProperty("user.dir") + System.getProperty("file.separator")
                + new Proprietes().getClass().getPackage().getName()
                + System.getProperty("file.separator") + "Settings.properties";

        return nomFichier;
    }

    public Properties getProperties() {
        try {
            prop.load(new FileInputStream(getNomFichierProperties()));
        }
        catch(FileNotFoundException e) { LOGGER.severe("Fichier de propriétés non trouvé"); }
        catch(IOException e) { LOGGER.severe(e.getMessage()); }

        return prop;
    }

}
