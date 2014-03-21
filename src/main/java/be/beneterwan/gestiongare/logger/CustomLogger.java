package be.beneterwan.gestiongare.logger;

import java.util.logging.Logger;

/**
 * @author bendem
 */
public class CustomLogger extends Logger {

    public CustomLogger(String name) {
        super(name, null);
        addHandler(new CustomHandler());
    }

}
