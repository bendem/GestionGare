package be.beneterwan.gestiongare.commons.network.receiver;

import be.beneterwan.gestiongare.commons.network.messages.Message;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bendem & Curlybear
 */
public class NetworkReceiver {

    private final List<MessageListener> handlers;
    private final ReceiverThread receiverThread;
    private int port;

    public NetworkReceiver() {
        this(0);
    }

    public NetworkReceiver(int port) {
        handlers = new ArrayList<>();
        receiverThread = new ReceiverThread(this);
        this.port = port;
    }

    public void addMessageListener(MessageListener listener) {
        handlers.add(listener);
    }

    /* package */ void dispatchMessage(String message) {
        MessageEvent event = new MessageEvent(Message.deserialize(message), this);
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

    public boolean isRunning() {
        return receiverThread.isRunning();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
