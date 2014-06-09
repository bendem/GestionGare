package be.beneterwan.gestiongare.commons.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class CustomLogger extends Logger {

    private static final String LOG_FOLDER_PATH = "." + File.separator + "logs" + File.separator;
    private static final Formatter formatter = new CustomFormatter();
    private static final Set<String> firstRun = new HashSet<>();

    private final File logFile;

    public CustomLogger(Class<?> clazz) {
        super(clazz.getSimpleName(), null);

        String jarName = getJarName(clazz);
        logFile = new File(LOG_FOLDER_PATH + "log_" + jarName + ".txt");

        // Move logs when the CustomLogger is initialized for the first time for a specific jar
        if(!firstRun.contains(jarName)) {
            backupLogs();
            firstRun.add(jarName);
        }

        // Set global level to all and refine it in the handlers
        setLevel(Level.ALL);

        // Log to console
        CustomHandler consoleHandler = new CustomHandler(new OutputStreamWriter(System.out), formatter);
        consoleHandler.setLevel(Level.ALL);
        addHandler(consoleHandler);

        // Log to file
        CustomHandler fileHandler = null;
        try {
            File logFolder = logFile.getParentFile();
            if(!logFolder.exists()) {
                logFolder.mkdir();
            }
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

    private String getJarName(Class<?> clazz) {
        return new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
    }

    private void createLogFolder(File folder) {
        if(!folder.exists()) {
            folder.mkdir();
        }
    }

    private void backupLogs() {
        File previousLogs = new File(LOG_FOLDER_PATH + "previous_" + logFile.getName());
        if(previousLogs.exists()) {
            previousLogs.delete();
        }
        if(logFile.exists()) {
            logFile.renameTo(previousLogs);
        }
    }

}
