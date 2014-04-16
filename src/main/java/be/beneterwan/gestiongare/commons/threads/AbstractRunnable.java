package be.beneterwan.gestiongare.commons.threads;

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
            pause(100);
        }
        shutdown();
    }

    protected abstract void work();
    protected abstract void shutdown();

    public void cancel() {
        this.cancelled = true;
    }

    protected void pause(long time) {
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            // Discard silently
        }
    }

}
