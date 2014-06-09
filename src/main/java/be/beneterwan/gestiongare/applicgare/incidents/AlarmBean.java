package be.beneterwan.gestiongare.applicgare.incidents;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bendem & Curlybear
 */
public class AlarmBean extends AbstractBean implements PropertyChangeListener {

    private final StateBean stateBean;
    private final Set<IncidentListener> handlers;

    public AlarmBean() {
        handlers = new HashSet<>();
        stateBean = (StateBean) instanciate(StateBean.class);
        stateBean.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if(((String) pce.getNewValue()).equalsIgnoreCase("nothing")) {
            return;
        }
        IncidentEvent incidentEvent = new IncidentEvent(pce.getSource(), (String) pce.getNewValue());
        for(IncidentListener incidentListener : handlers) {
            incidentListener.onIncident(incidentEvent);
        }
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

    public void reset() {
        stateBean.reset();
    }

    @Override
    public void kill() {
        stateBean.kill();
    }

}
