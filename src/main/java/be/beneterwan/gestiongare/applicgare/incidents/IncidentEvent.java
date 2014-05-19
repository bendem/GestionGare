package be.beneterwan.gestiongare.applicgare.incidents;

import java.util.Date;
import java.util.EventObject;

/**
 * @author bendem & Curlybear
 */
public class IncidentEvent extends EventObject {

    private final String incidentMessage;
    private final Date incidentDate;

    public IncidentEvent(Object source, String incidentMessage) {
        super(source);
        this.incidentMessage = incidentMessage;
        this.incidentDate = new Date();
    }

    public String getIncidentMessage() {
        return incidentMessage;
    }

    public Date getIncidentDate() {
        return incidentDate;
    }
}
