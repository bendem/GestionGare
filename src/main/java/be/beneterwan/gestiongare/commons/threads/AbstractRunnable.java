package be.beneterwan.gestiongare.commons.threads;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public abstract class AbstractRunnable implements Runnable {

    private static final Logger LOGGER = new CustomLogger(AbstractRunnable.class.getSimpleName());

    private final Thread thread;
    private boolean cancelled;

    public AbstractRunnable() {
        cancelled = false;
        thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    public Thread getThread() {
        return thread;
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
        try {
            thread.join(500);
        } catch(InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "Failed to join canceled thread", ex);
        }
    }

    protected void pause(long time) {
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            // Discard silently
        }
    }

}
