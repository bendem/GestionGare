package be.beneterwan.gestiongare.applicgare.incidents;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * @author bendem & Curlybear
 */
public abstract class AbstractBean implements Serializable {

    protected final PropertyChangeSupport propertySupport;

    protected AbstractBean()  {
        propertySupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

}