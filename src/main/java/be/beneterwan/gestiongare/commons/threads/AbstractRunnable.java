package be.beneterwan.gestiongare.commons.threads;

/**
 * @author bendem et Curlybear
 */
public abstract class AbstractRunnable implements Runnable {

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
