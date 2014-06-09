package be.beneterwan.gestiongare.applicpostes;

import be.beneterwan.gestiongare.applicpostes.handlers.ButtonMsgRecuHandler;
import be.beneterwan.gestiongare.applicpostes.handlers.ButtonSignalPassageTrainHandler;
import be.beneterwan.gestiongare.applicpostes.handlers.MessageHandler;
import be.beneterwan.gestiongare.applicpostes.handlers.PosteTypeChoiceHandler;
import be.beneterwan.gestiongare.commons.ApplicationConfig;
import be.beneterwan.gestiongare.commons.config.ConfigManager;
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

    private static final Logger LOGGER = new CustomLogger(ApplicPostes.class);
    private final NetworkEventManager eventManager;
    private final ApplicPostesFrame frame;
    private final ConfigManager configManager;
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
        configManager = new ConfigManager(ConfigManager.CONFIG_FILE_NAME);
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

        int portIn, portOut;
        String ipOut;
        ipOut = configManager.getString(ApplicationConfig.IpApplicGare);
        if(type == Type.In) {
            portIn = configManager.getInt(ApplicationConfig.PortApplicGareToApplicIn);
            portOut = configManager.getInt(ApplicationConfig.PortApplicInToApplicGare);
        } else {
            portIn = configManager.getInt(ApplicationConfig.PortApplicGareToApplicOut);
            portOut = configManager.getInt(ApplicationConfig.PortApplicOutToApplicGare);
        }
        networkSender = new NetworkStringSender(ipOut, portOut);
        networkReceiver = new NetworkReceiver(portIn);
        networkReceiver.start();
        eventManager.addListener(networkReceiver, new MessageHandler(this));

        frame.startApplication();
    }

    public void stopThreads() {
        LOGGER.info("Stopping threads");
        if(networkReceiver != null && networkReceiver.isRunning()) {
            networkReceiver.stop();
        }
        if(networkSender != null) {
            networkSender.endSending();
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

    public NetworkStringSender getNetworkSender() {
        return networkSender;
    }

    public static void main(String[] args) {
        if(args.length > 0 && (args[0].equals("In") || args[0].equals("Out"))) {
            new ApplicPostes(args[0]);
        } else {
            new ApplicPostes();
        }
    }

    public enum Type {
        In, Out;
    }

}
