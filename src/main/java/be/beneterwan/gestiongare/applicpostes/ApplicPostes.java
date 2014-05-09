package be.beneterwan.gestiongare.applicpostes;

import be.beneterwan.gestiongare.applicpostes.handlers.PosteTypeChoiceHandler;
import be.beneterwan.gestiongare.commons.eventmanagement.EventManager;
import java.util.Queue;

/**
 * @author bendem et Curlybear
 */
public class ApplicPostes {

    private static ApplicPostes instance;
    private final EventManager eventManager;
    private final ApplicPostesFrame frame;
    private Type type;
    private final Queue<String> applicPostesMessages;

    public ApplicPostes() {
        frame = new ApplicPostesFrame(this);
        eventManager = new EventManager();
        eventManager.addListener(frame.getButtonValider(), new PosteTypeChoiceHandler(this));
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

    public void setType(Type type) {
        this.type = type;
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
