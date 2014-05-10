package be.beneterwan.gestiongare.applicpostes;

import static be.beneterwan.gestiongare.applicgare.ApplicGareFrame.LOGGER;
import be.beneterwan.gestiongare.applicpostes.handlers.MessageHandler;
import be.beneterwan.gestiongare.applicpostes.handlers.PosteTypeChoiceHandler;
import be.beneterwan.gestiongare.commons.eventmanagement.NetworkEventManager;
import be.beneterwan.gestiongare.commons.networkreceiver.NetworkReceiver;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author bendem & Curlybear
 */
public class ApplicPostes {

    private static ApplicPostes instance;
    private final NetworkEventManager eventManager;
    private final ApplicPostesFrame frame;
    private final NetworkReceiver networkReceiver;
    private Type type;

    public ApplicPostes() {
        frame = new ApplicPostesFrame(this);
        eventManager = new NetworkEventManager();
        eventManager.addListener(frame.getButtonValider(), new PosteTypeChoiceHandler(this));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                stopThreads();
                frame.dispose();
            }
        });
        networkReceiver = new NetworkReceiver();
    }

    public void startApplication(Type type) {
        if(this.type != null) {
            throw new IllegalStateException("Application already started");
        }

        this.type = type;

        networkReceiver.setPort(type.equals(Type.In) ? 50_000 : 50_001);
        eventManager.addListener(networkReceiver, new MessageHandler(this));

        startThreads();

        frame.startApplication();
    }

    public void startThreads() {
        LOGGER.info("Starting threads");
        if(!networkReceiver.isRunning()) {
            networkReceiver.start();
        }
    }

    public void stopThreads() {
        LOGGER.info("Stopping threads");
        if(networkReceiver.isRunning()) {
            networkReceiver.stop();
        }
    }

    public NetworkEventManager getEventManager() {
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
