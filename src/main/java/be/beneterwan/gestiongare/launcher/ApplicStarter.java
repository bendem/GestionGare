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

    private static final Logger LOGGER = new CustomLogger(ApplicStarter.class);

    private final Class<?> clazz;
    private final Class<?>[] argsType;
    private final Object[] args;
    private Object instance;

    public ApplicStarter(Class<?> clazz, String ... args) {
        this.clazz = clazz;
        this.args = args;
        this.argsType = new Class<?>[args.length];
        for(int i = 0; i < args.length; i++) {
            this.argsType[i] = String.class;
        }
    }

    @Override
    public void run() {
        Constructor<?> constructor = null;
        try {
            constructor = clazz.getConstructor(argsType);
        } catch(NoSuchMethodException ex) {
            LOGGER.log(Level.SEVERE, "Could not get constructor of " + clazz.getName() + " using " + args.length + " parameters", ex);
        } catch(SecurityException ex) {
            LOGGER.log(Level.SEVERE, "Constructor of " + clazz.getName() + " is not accessible", ex);
        }

        if(constructor == null) {
            LOGGER.severe("Could not launch " + clazz.getName());
            return;
        }

        try {
            instance = constructor.newInstance(args);
        } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.log(Level.SEVERE, "Could not instanciate " + clazz.getName(), ex);
        }
    }

}
