package be.beneterwan.gestiongare.applicpostes;

import be.beneterwan.gestiongare.applicpostes.handlers.PosteTypeChoiceHandler;
import be.beneterwan.gestiongare.commons.eventmanagement.EventManager;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author bendem et Curlybear
 */
public class ApplicPostes {

    private static ApplicPostes instance;
    private final EventManager eventManager;
    private final ApplicPostesFrame frame;
    private final Queue<String> applicPostesMessages;
    private ApplicGareReceiver receiver;
    private Type type;

    public ApplicPostes() {
        frame = new ApplicPostesFrame(this);
        eventManager = new EventManager();
        applicPostesMessages= new ConcurrentLinkedQueue<>();
        eventManager.addListener(frame.getButtonValider(), new PosteTypeChoiceHandler(this));
    }

    public void startApplication(Type type) {
        if(type != null) {
            throw new IllegalStateException("Application already started");
        }
        this.type = type;
        receiver = new ApplicGareReceiver(this);
        receiver.start();
        frame.startApplication();
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public ApplicPostesFrame getFrame() {
        return frame;
    }

    public Type getType() {
        return type;
    }

    void addApplicPostesMessage(String message) {
        applicPostesMessages.add(message);
    }

    public static void main(String[] args) {
        instance = new ApplicPostes();
    }

    public enum Type {
        In, Out
    }

}
