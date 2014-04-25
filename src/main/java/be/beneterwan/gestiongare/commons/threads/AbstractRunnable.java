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
    private boolean running;

    public AbstractRunnable() {
        cancelled = false;
        running = false;
        thread = new Thread(this);
    }

    public final void start() {
        thread.start();
    }

    public final Thread getThread() {
        return thread;
    }

    public final synchronized boolean isRunning() {
        return running;
    }

    @Override
    public final void run() {
        running = true;
        startup();
        while(!cancelled) {
            work();
            pause(100);
        }
        shutdown();
        running = false;
    }

    protected void startup() {}
    protected abstract void work();
    protected void shutdown() {}

    public final synchronized void cancel() {
        this.cancelled = true;
        try {
            thread.join(500);
        } catch(InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "Failed to join canceled thread", ex);
        }
    }

    protected final void pause(long time) {
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            // Discard silently
        }
    }

}
