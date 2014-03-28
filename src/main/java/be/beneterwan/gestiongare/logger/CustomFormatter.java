package be.beneterwan.gestiongare.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author bendem et Curlybear
 */
public class CustomFormatter extends Formatter {

    @Override
    public String format(LogRecord lr) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, SSS");
        return "[" + lr.getLoggerName() + " " + sdf.format(new Date()) +  "] [" + lr.getLevel().getName() + "] " + lr.getMessage();
    }

}
