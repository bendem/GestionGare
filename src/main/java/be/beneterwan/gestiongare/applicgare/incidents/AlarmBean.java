package be.beneterwan.gestiongare.applicgare.incidents;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author bendem & Curlybear
 */
public class AlarmBean extends AbstractBean implements PropertyChangeListener {

    public AlarmBean() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        IncidentEvent incidentEvent = new IncidentEvent(pce.getSource(), (String) pce.getNewValue());
    }

}
