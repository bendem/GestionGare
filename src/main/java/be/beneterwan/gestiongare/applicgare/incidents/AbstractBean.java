package be.beneterwan.gestiongare.applicgare.incidents;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.beans.Beans;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public abstract class AbstractBean implements Serializable {

    private static final Logger LOGGER = new CustomLogger(AbstractBean.class);

    public static AbstractBean instanciate(Class<?extends AbstractBean> clazz) {
        try {
            return (AbstractBean) Beans.instantiate(null, clazz.getName());
        } catch(IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Could not instanciate " + clazz.getName(), ex);
        }
        return null;
    }

    public abstract void kill();

}