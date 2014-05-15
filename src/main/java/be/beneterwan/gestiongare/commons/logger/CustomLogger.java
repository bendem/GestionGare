package be.beneterwan.gestiongare.commons.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class CustomLogger extends Logger {

    private static final Formatter formatter = new CustomFormatter();
    private static final File logFile;

    static {
        logFile = createLogFile();
    }

    public CustomLogger(String name) {
        super(name, null);

        // Set global level to all and refine it in the handlers
        setLevel(Level.ALL);

        // Log to console
        CustomHandler consoleHandler = new CustomHandler(new OutputStreamWriter(System.out), formatter);
        consoleHandler.setLevel(Level.ALL);
        addHandler(consoleHandler);

        // Log to file
        CustomHandler fileHandler = null;
        try {
            fileHandler = new CustomHandler(new BufferedWriter(new FileWriter(logFile, true)), formatter);
        } catch(IOException ex) {
            Logger.getLogger(CustomLogger.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        if(fileHandler != null) {
            fileHandler.setLevel(Level.ALL);
            addHandler(fileHandler);
        }
    }

    private static File createLogFile() {
        File file = new File("." + File.separator + "logs" + File.separator + "logs.txt");
        createLogFolder(file.getParentFile());
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException ex) {
                Logger.getLogger(CustomLogger.class.getName())
                    .log(Level.SEVERE, "Could not create log file", ex);
                return null;
            }
        }
        return file;
    }

    private static void createLogFolder(File folder) {
        if(!folder.exists()) {
            folder.mkdir();
        }
    }

}
