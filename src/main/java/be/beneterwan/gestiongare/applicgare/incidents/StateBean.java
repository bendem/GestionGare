package be.beneterwan.gestiongare.applicgare.incidents;

import be.beneterwan.gestiongare.commons.ApplicationConfig;
import be.beneterwan.gestiongare.commons.config.ConfigManager;
import java.util.Random;

/**
 * @author bendem & Curlybear
 */
public class StateBean extends AbstractBean implements NumberConsumer {

    private final Random rnd;
    private final ConfigManager configManager;
    private String info = "Nothing";

    public StateBean() {
        rnd = new Random();
        configManager = new ConfigManager(ConfigManager.CONFIG_FILE_NAME, true);
    }

    @Override
    public void consumeNumber(double number) {
        // One incident at a time, please...
        if(!info.equals("Nothing")) {
            return;
        }

        int nbType = rnd.nextInt(20);
        String old = info;
        IncidentType type = IncidentType.getFromNumber(nbType);

        if(type == null) {
            return;
        }

        switch(type) {
            case Retard:
                info = String.format(configManager.getString(ApplicationConfig.MessageIncidentRetard), rnd.nextInt(25)+5);
                break;
            case Manifestation:
                info = configManager.getString(ApplicationConfig.MessageIncidentManifestation);
                break;
            case Greve:
                info = configManager.getString(ApplicationConfig.MessageIncidentGreve);
                break;
        }

        propertySupport.firePropertyChange("info", old, info);
    }

    public void reset() {
        String old = info;
        info = "Nothing";
        propertySupport.firePropertyChange("info", old, info);
    }

}
