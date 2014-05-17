package be.beneterwan.gestiongare.applicgare.incidents;

import java.util.Random;

/**
 * @author bendem & Curlybear
 */
public class StateBean extends AbstractBean implements NumberConsumer {

    private String info = "Nothing";

    @Override
    public void consumeNumber(double number) {
        int nbType = new Random().nextInt(20);
        IncidentType type = IncidentType.getFromNumber(nbType);
        if(type != null) {
            propertySupport.firePropertyChange("info", "old_value", info);
        }
    }

}
