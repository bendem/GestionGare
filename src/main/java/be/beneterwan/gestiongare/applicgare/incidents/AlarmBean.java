package be.beneterwan.gestiongare.applicgare.incidents;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bendem & Curlybear
 */
public class AlarmBean extends AbstractBean implements PropertyChangeListener {

    private final Set<IncidentListener> handlers;

    public AlarmBean() {
        handlers = new HashSet<>();
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

}
