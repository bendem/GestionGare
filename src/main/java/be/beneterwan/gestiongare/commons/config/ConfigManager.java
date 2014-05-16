package be.beneterwan.gestiongare.commons.config;

import be.beneterwan.gestiongare.commons.ResourceManager;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class ConfigManager {

    private static final Logger LOGGER = new CustomLogger(ConfigManager.class);
    public static final String CONFIG_FOLDER_PATH = "." + File.separator + "config" + File.separator;
    public static final String CONFIG_FILE_NAME = "settings.properties";

    private final Properties properties;
    private final File configFile;

    public ConfigManager(String filename, boolean copyDefault) {
        configFile = new File(CONFIG_FOLDER_PATH + filename);
        properties = new Properties();

        if(copyDefault) {
            copyDefaultConfig();
        }

        try {
            properties.load(new FileInputStream(configFile));
        } catch(IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            throw new RuntimeException("Could not load application config", ex);
        }
    }

    public String getString(Config node) {
        String property = properties.getProperty(node.getNode());
        return property == null ? node.getDefaultValue().toString() : property;
    }

    public int getInt(Config node) {
        return Integer.parseInt(getString(node));
    }

    public void set(Config node, String value) {
        properties.setProperty(node.getNode(), value);
    }

    public void set(Config node, int value) {
        properties.setProperty(node.getNode(), String.valueOf(value));
    }

    public boolean save() {
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(configFile);
        } catch(FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            properties.store(outputStream, null);
            return true;
        } catch(IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private void copyDefaultConfig() {
        ResourceManager.copyRessourceTo(CONFIG_FILE_NAME, new File(CONFIG_FOLDER_PATH), false);
    }

}
