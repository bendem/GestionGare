package be.beneterwan.gestiongare.commons.logger;

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
        String header = "[" + lr.getLevel().getName() + " " + sdf.format(new Date()) + "] [" + lr.getLoggerName() + "] ";
        StringBuilder message = new StringBuilder(header);
        message.append(lr.getMessage());

        Throwable error = lr.getThrown();
        if(error != null) {
            message
                .append('\n')
                .append(header)
                .append("Error : ")
                .append(error.getMessage());

            Throwable cause = error.getCause();
            if(cause != null) {
                message
                    .append('\n')
                    .append(header)
                    .append("Caused by : ")
                    .append(cause.getMessage());
            }


        }
        return message.toString();
    }

}
