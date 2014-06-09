package be.beneterwan.gestiongare.commons.threads;

import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bendem & Curlybear
 */
public abstract class AbstractRunnable implements Runnable {

    private static final Logger LOGGER = new CustomLogger(AbstractRunnable.class);

    private final int waitTime;
    private final Thread thread;
    private volatile boolean cancelled;
    private volatile boolean running;
    private volatile boolean idling;
    private int delay = 0;

    public AbstractRunnable() {
        this(100);
    }

    public AbstractRunnable(int waitTime) {
        cancelled = false;
        running = false;
        idling = false;
        thread = new Thread(this);
        this.waitTime = waitTime;
    }

    public final void start(int delay) {
        if(delay < 1) {
            throw new IllegalArgumentException("Can't schedule thread in the past...");
        }
        this.delay = delay;
        start();
    }

    public final void start() {
        if(running) {
            throw new IllegalStateException("Thread is already running");
        }
        thread.start();
    }

    public final synchronized boolean isRunning() {
        return running;
    }

    @Override
    public final void run() {
        running = true;
        if(delay > 0) {
            pause(delay);
        }
        startup();
        while(!cancelled) {
            work();
            pause(waitTime);
        }
        shutdown();
        running = false;
    }

    protected void startup() {}
    protected abstract void work();
    protected void shutdown() {}

    public final synchronized void cancel() {
        if(!running) {
            throw new IllegalStateException("Thread is not running");
        }
        this.cancelled = true;
        if(idling) {
            thread.interrupt();
        }
        try {
            thread.join(500);
        } catch(InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "Failed to join canceled thread", ex);
        }
    }

    protected final void pause(long time) {
        idling = true;
        try {
            Thread.sleep(time);
        } catch(InterruptedException ignored) {
        } finally {
            idling = false;
        }
    }

}
