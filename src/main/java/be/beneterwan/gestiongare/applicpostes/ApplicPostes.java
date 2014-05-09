package be.beneterwan.gestiongare.applicpostes;

import be.beneterwan.gestiongare.applicpostes.handlers.MessageHandler;
import be.beneterwan.gestiongare.applicpostes.handlers.PosteTypeChoiceHandler;
import be.beneterwan.gestiongare.applicpostes.receiver.Receiver;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author bendem et Curlybear
 */
public class ApplicPostes {

    private static ApplicPostes instance;
    private final ApplicPostesEventManager eventManager;
    private final ApplicPostesFrame frame;
    private final Receiver receiver;
    private Type type;

    public ApplicPostes() {
        frame = new ApplicPostesFrame(this);
        eventManager = new ApplicPostesEventManager();
        eventManager.addListener(frame.getButtonValider(), new PosteTypeChoiceHandler(this));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                receiver.stop();
                frame.dispose();
            }
        });
        receiver = new Receiver();
    }

    public void startApplication(Type type) {
        if(this.type != null) {
            throw new IllegalStateException("Application already started");
        }

        this.type = type;

        receiver.setPort(type.equals(Type.In) ? 50_000 : 50_001);
        eventManager.addListener(receiver, new MessageHandler(this));
        receiver.start();

        frame.startApplication();
    }

    public ApplicPostesEventManager getEventManager() {
        return eventManager;
    }

    public ApplicPostesFrame getFrame() {
        return frame;
    }

    public Type getType() {
        return type;
    }

    public static void main(String[] args) {
        instance = new ApplicPostes();
    }

    public enum Type {
        In, Out
    }

}
