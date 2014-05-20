package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.awt.Desktop;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * @author bendem & Curlybear
 */
public class MenuAideLogHandler implements EventHandler {

    private static final Logger LOGGER = new CustomLogger(MenuAideLogHandler.class);
    private static final Pattern pattern = Pattern.compile("^log_.*\\.jar\\.txt$");

    @Override
    public void execute(EventObject event) {
        LOGGER.info("Opening log files...");
        Desktop desk = Desktop.getDesktop();
        File logFolder = new File("./logs/");
        File[] listFiles = logFolder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.isDirectory()) {
                    return false;
                }
                return pattern.matcher(file.getName()).matches();
            }
        });
        for(File file : listFiles) {
            try {
                desk.open(file);
            } catch(IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }

}
