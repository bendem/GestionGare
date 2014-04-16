package be.beneterwan.gestiongare.commons.threads;

import be.beneterwan.gestiongare.commons.Tools;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public abstract class AbstractRunnable implements Runnable {

    private static final Logger LOGGER = new CustomLogger(AbstractRunnable.class.getSimpleName());

    private boolean cancelled;

    public AbstractRunnable() {
        cancelled = false;
    }

    @Override
    public void run() {
        while(!cancelled) {
            work();
            Tools.pause(100);
        }
    }

    protected abstract void work();

    public void cancel() {
        this.cancelled = true;
    }

}
