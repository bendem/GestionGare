package be.beneterwan.gestiongare.logger;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * @author bendem
 */
public class CustomHandler extends Handler {

    public CustomHandler() {
        setFormatter(new CustomFormatter());
    }

    @Override
    public void close() {}

    @Override
    public void flush() {}

    @Override
    public void publish(LogRecord lr) {
        System.out.println(getFormatter().format(lr));
    }

}