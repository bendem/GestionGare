package be.beneterwan.gestiongare.commons;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static be.beneterwan.gestiongare.commons.config.ConfigManager.CONFIG_FILE_NAME;
import static be.beneterwan.gestiongare.commons.config.ConfigManager.CONFIG_FOLDER_PATH;

/**
 * @author bendem & Curlybear
 */
public class ResourceManager {

    private static final Logger LOGGER = new CustomLogger(ResourceManager.class);

    public static URL getResourceFile(String filename) {
        return ResourceManager.class.getClassLoader().getResource(filename);
    }

    public static InputStream getRessourceStream(String filename) {
        URL url = getResourceFile(filename);

        if (url == null) {
            return null;
        }

        try {
            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException ex) {
            return null;
        }
    }

    public static boolean copyRessourceTo(String filename, File to, boolean replace) {
        InputStream in = getRessourceStream(filename);
        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + CONFIG_FILE_NAME + "' cannot be found");
        }

        File outFile = new File(CONFIG_FOLDER_PATH, CONFIG_FILE_NAME);
        File outDir = new File(CONFIG_FOLDER_PATH);

        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        if (!outFile.exists() || replace) {
            try (OutputStream out = new FileOutputStream(outFile)) {
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                return true;
            } catch(IOException ex) {
                LOGGER.log(Level.SEVERE, "Could not write to file", ex);
                return false;
            }
        } else {
            LOGGER.info(outFile.getName() + " already exists.");
            return !replace;
        }
    }

}
