package be.beneterwan.gestiongare.commons.logger;

import java.io.IOException;
import java.io.Writer;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class CustomHandler extends Handler {

    private final Writer writer;

    public CustomHandler(Writer writer, Formatter formatter) {
        setFormatter(formatter);
        this.writer = writer;
    }

    @Override
    public void close() throws SecurityException {
        try {
            writer.close();
        } catch(IOException ex) {
            Logger.getLogger(CustomHandler.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void flush() {
        try {
            writer.flush();
        } catch(IOException ex) {
            Logger.getLogger(CustomHandler.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void publish(LogRecord lr) {
        String toPublish = getFormatter().format(lr) + '\n';
        try {
            writer.write(toPublish, 0, toPublish.length());
            flush(); // TEMP, to remove when working...
        } catch(IOException ex) {
            Logger.getLogger(CustomHandler.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
