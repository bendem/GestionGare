package be.beneterwan.gestiongare.applicgare.incidents;

import be.beneterwan.gestiongare.commons.ApplicationConfig;
import be.beneterwan.gestiongare.commons.config.ConfigManager;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public class StateBean extends AbstractPropertyChangeSupportedBean implements NumberConsumer {

    private static final Logger LOGGER = new CustomLogger(StateBean.class);

    private final Random rnd;
    private final ThreadRandomGenerator threadRandomGenerator;
    private final ConfigManager configManager;
    private String info = "Nothing";

    public StateBean() {
        rnd = new Random();
        threadRandomGenerator = new ThreadRandomGenerator(this, 15);
        configManager = new ConfigManager(ConfigManager.CONFIG_FILE_NAME, true);
        threadRandomGenerator.start(5000);
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

        LOGGER.fine("Stuff was chosen: " + nbType);

        if(type == null) {
            return;
        }

        LOGGER.fine("Stuff happened: " + type.name());

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

    public void kill() {
        threadRandomGenerator.cancel();
    }

}
