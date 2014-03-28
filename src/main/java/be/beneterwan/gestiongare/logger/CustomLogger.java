package be.beneterwan.gestiongare.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem
 */
public class CustomLogger extends Logger {

    public CustomLogger(String name) {
        super(name, null);
        setLevel(Level.ALL);
        addHandler(new CustomHandler());
    }

}