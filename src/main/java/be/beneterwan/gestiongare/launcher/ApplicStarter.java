package be.beneterwan.gestiongare.launcher;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class ApplicStarter implements Runnable {

    private static final Logger LOGGER = new CustomLogger(ApplicStarter.class.getSimpleName());
    private final Class<?> clazz;
    private Object instance;

    public ApplicStarter(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void run() {
        Constructor<?> constructor = null;
        try {
            constructor = clazz.getConstructor();
        } catch(NoSuchMethodException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, "Could not get default constructor of " + clazz.getName(), ex);
        }

        if(constructor == null) {
            LOGGER.severe("Could not launch " + clazz.getName());
            return;
        }

        try {
            instance = constructor.newInstance();
        } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.log(Level.SEVERE, "Could not instanciate " + clazz.getName(), ex);
        }
    }

}
