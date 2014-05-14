package be.beneterwan.gestiongare.applicgare.incidents;

import java.util.Random;

/**
 * @author bendem & Curlybear
 */
public class StateBean implements NumberConsumer {

    private String info = "Nothing";

    @Override
    public void consumeNumber(double number) {
        int nbType = new Random().nextInt(20);
        IncidentType type = IncidentType.getFromNumber(nbType);
        if(type != null) {
            firePropertyChangeEvent(/**/);
        }
    }

    private void firePropertyChangeEvent() {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
