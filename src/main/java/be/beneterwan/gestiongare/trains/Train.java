package be.beneterwan.gestiongare.trains;

import be.beneterwan.gestiongare.logger.CustomLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author bendem
 */
public class Train {

    public static final Logger LOGGER = new CustomLogger(Train.class.getSimpleName());

    protected final List<VehiculeRail> derp = new ArrayList<>();

}
