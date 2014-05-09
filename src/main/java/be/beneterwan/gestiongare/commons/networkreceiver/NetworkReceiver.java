package be.beneterwan.gestiongare.commons.networkreceiver;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author bendem et Curlybear
 */
public class NetworkReceiver {

    private final List<MessageListener> handlers;
    private final ReceiverThread receiverThread;
    private final Queue<String> messages;
    private int port;

    public NetworkReceiver() {
        handlers = new ArrayList<>();
        receiverThread = new ReceiverThread(this);
        messages = new ConcurrentLinkedQueue<>();
        port = 0;
    }

    public void addMessageListener(MessageListener listener) {
        handlers.add(listener);
    }

    /* package */ void dispatchMessage(String message) {
        MessageEvent event = new MessageEvent(message, this);
        for(MessageListener listener : handlers) {
            listener.onMessage(event);
        }
    }

    public void start() {
        receiverThread.start();
    }

    public void stop() {
        receiverThread.cancel();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
