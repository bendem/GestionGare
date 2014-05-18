package be.beneterwan.gestiongare.applicgare.incidents;

import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class IncidentEvent extends EventObject {

    private final String incidentMessage;

    public IncidentEvent(Object source, String incidentMessage) {
        super(source);
        this.incidentMessage = incidentMessage;
    }

    public String getIncidentMessage() {
        return incidentMessage;
    }

}
