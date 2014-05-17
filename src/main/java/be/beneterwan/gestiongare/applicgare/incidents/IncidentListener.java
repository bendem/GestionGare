package be.beneterwan.gestiongare.applicgare.incidents;

import java.util.EventListener;

/**
 * @author bendem & Curlybear
 */
public interface IncidentListener extends EventListener {

    public void onIncident(IncidentEvent event);

}
