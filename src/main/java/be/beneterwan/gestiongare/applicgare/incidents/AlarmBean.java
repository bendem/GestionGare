package be.beneterwan.gestiongare.applicgare.incidents;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.beans.Beans;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class AlarmBean extends AbstractBean implements PropertyChangeListener {

    private static final Logger LOGGER = new CustomLogger(AlarmBean.class);
    private final StateBean stateBean;
    private final Set<IncidentListener> handlers;

    public AlarmBean() {
        handlers = new HashSet<>();
        stateBean = (StateBean) instanciate(StateBean.class.getName());
        stateBean.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        IncidentEvent incidentEvent = new IncidentEvent(pce.getSource(), (String) pce.getNewValue());
    }

    public void addIncidentListener(IncidentListener listener) {
        if(!handlers.contains(listener)) {
            handlers.add(listener);
        }
    }

    public void removeIncidentListener(IncidentListener listener) {
        if(handlers.contains(listener)) {
            handlers.remove(listener);
        }
    }

    private Object instanciate(String className) {
        try {
            return Beans.instantiate(null, className);
        } catch(IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Could not instanciate " + className, ex);
        }
        return null;
    }
}
