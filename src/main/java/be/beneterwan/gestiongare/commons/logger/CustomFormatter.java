package be.beneterwan.gestiongare.commons.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author bendem & Curlybear
 */
public class CustomFormatter extends Formatter {

    @Override
    public String format(LogRecord lr) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, SSS");
        String header = "[" + lr.getLevel().getName() + " " + sdf.format(new Date()) + "] [" + lr.getLoggerName() + "] ";
        StringBuilder message = new StringBuilder();

        String logMessage = lr.getMessage();
        if(logMessage != null) {
            message.append(header).append(logMessage);
        }

        Throwable error = lr.getThrown();
        if(error != null) {
            message
                .append('\n')
                .append(header)
                .append("Error : ")
                .append(error.getMessage() == null ? error.getClass().getName() : error.getMessage())
                .append(getStackTraceString(error.getStackTrace()));

            Throwable cause = error.getCause();
            while(cause != null) {
                message
                    .append('\n')
                    .append(header)
                    .append("Caused by : ")
                    .append(error.getMessage() == null ? error.getClass().getName() : error.getMessage())
                    .append(getStackTraceString(cause.getStackTrace()));
                cause = cause.getCause();
            }


        }
        return message.toString();
    }

    private String getStackTraceString(StackTraceElement[] stackTrace) {
        StringBuilder stack = new StringBuilder();
        for(StackTraceElement stackTraceElement : stackTrace) {
            stack.append("\n\t").append(stackTraceElement.toString());
        }
        return stack.toString();
    }

}
