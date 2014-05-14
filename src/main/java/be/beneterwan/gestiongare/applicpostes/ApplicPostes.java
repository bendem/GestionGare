package be.beneterwan.gestiongare.applicpostes;

import be.beneterwan.gestiongare.applicpostes.handlers.ButtonMsgRecuHandler;
import be.beneterwan.gestiongare.applicpostes.handlers.ButtonSignalPassageTrainHandler;
import be.beneterwan.gestiongare.applicpostes.handlers.MessageHandler;
import be.beneterwan.gestiongare.applicpostes.handlers.PosteTypeChoiceHandler;
import be.beneterwan.gestiongare.commons.eventmanagement.NetworkEventManager;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.network.receiver.NetworkReceiver;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import network.NetworkStringSender;

/**
 * @author bendem & Curlybear
 */
public class ApplicPostes {

    private static final Logger LOGGER = new CustomLogger(ApplicPostes.class.getSimpleName());
    private static ApplicPostes instance;
    private final NetworkEventManager eventManager;
    private final ApplicPostesFrame frame;
    private NetworkStringSender networkSender;
    private NetworkReceiver networkReceiver;
    private Type type;

    public ApplicPostes(String type) {
        this();
        Type t = Type.valueOf(type);
        LOGGER.fine("Auto starting application with type " + type);
        frame.getComboBoxPostes().setSelectedItem(t);
        startApplication(t);
    }

    public ApplicPostes() {
        LOGGER.info("Starting up application...");
        frame = new ApplicPostesFrame(this);
        eventManager = new NetworkEventManager();
        eventManager.addListener(frame.getButtonValider(), new PosteTypeChoiceHandler(this));
        eventManager.addListener(frame.getButtonMsgRecu(), new ButtonMsgRecuHandler(this));
        eventManager.addListener(frame.getButtonSignalPassageTrain(), new ButtonSignalPassageTrainHandler(this));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                stopThreads();
                frame.dispose();
            }
        });
    }

    public void startApplication(Type type) {
        if(this.type != null) {
            throw new IllegalStateException("Application already started");
        }

        this.type = type;

        networkSender = new NetworkStringSender("127.0.0.1", type.getSendPort());
        networkReceiver = new NetworkReceiver(type.getReceivePort());
        networkReceiver.start();
        eventManager.addListener(networkReceiver, new MessageHandler(this));

        frame.startApplication();
    }

    public void stopThreads() {
        LOGGER.info("Stopping threads");
        if(networkReceiver.isRunning()) {
            networkReceiver.stop();
        }
        networkSender.endSending();
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

    public NetworkStringSender getNetworkSender() {
        return networkSender;
    }

    public static void main(String[] args) {
        instance = new ApplicPostes();
    }

    public enum Type {
        In(50_010, 50_000), Out(50_011, 50_001);

        private final int sendPort;
        private final int receivePort;

        Type(int sendPort, int receivePort) {
            this.sendPort = sendPort;
            this.receivePort = receivePort;
        }

        public int getSendPort() {
            return sendPort;
        }

        public int getReceivePort() {
            return receivePort;
        }

    }

}
