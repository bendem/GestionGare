package be.beneterwan.gestiongare.applicgare.incidents;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Random;

/**
 * @author bendem & Curlybear
 */
public class StateBean implements NumberConsumer, Serializable {

    private final PropertyChangeSupport propertySupport;
    private String info = "Nothing";

    public StateBean()  {
        propertySupport = new PropertyChangeSupport(this);
    }

    @Override
    public void consumeNumber(double number) {
        int nbType = new Random().nextInt(20);
        IncidentType type = IncidentType.getFromNumber(nbType);
        if(type != null) {
            propertySupport.firePropertyChange("", "", "");
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

}
