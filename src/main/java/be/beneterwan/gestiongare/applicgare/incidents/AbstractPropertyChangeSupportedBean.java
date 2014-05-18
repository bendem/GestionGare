package be.beneterwan.gestiongare.applicgare.incidents;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author bendem & Curlybear
 */
public abstract class AbstractPropertyChangeSupportedBean extends AbstractBean {

    protected final PropertyChangeSupport propertySupport;

    protected AbstractPropertyChangeSupportedBean()  {
        propertySupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

}
