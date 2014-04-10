package be.beneterwan.gestiongare.commons.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public class CustomLogger extends Logger {

    public CustomLogger(String name) {
        super(name, null);
        setLevel(Level.ALL);
        addHandler(new CustomHandler());
    }

}
