package be.beneterwan.gestiongare.applicdepot;

import be.beneterwan.gestiongare.applicdepot.handlers.ButtonMsgRecuHandler;
import be.beneterwan.gestiongare.applicdepot.handlers.ButtonSignalArriveeTrainHandler;
import be.beneterwan.gestiongare.applicdepot.handlers.ButtonValiderHandler;
import be.beneterwan.gestiongare.applicdepot.handlers.ComboBoxVoieHandler;
import be.beneterwan.gestiongare.applicdepot.handlers.MessageHandler;
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
public class ApplicDepot {

    private static final Logger LOGGER = new CustomLogger(ApplicDepot.class);
    protected static ApplicDepotFrame applicDepotFrame;
    private final NetworkReceiver networkReceiver;
    private final NetworkStringSender networkSender;
    private final NetworkEventManager eventManager;
    private final ConfigManager configManager;

    public ApplicDepot() {
        LOGGER.info("Starting up application...");
        configManager = new ConfigManager(ConfigManager.CONFIG_FILE_NAME);
        applicDepotFrame = new ApplicDepotFrame(this);
        applicDepotFrame.setVisible(true);
        applicDepotFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                stopThreads();
                applicDepotFrame.dispose();
            }
        });
        eventManager = new NetworkEventManager();
        networkReceiver = new NetworkReceiver(configManager.getInt(ApplicationConfig.PortApplicGareToApplicDepot));
        networkSender = new NetworkStringSender(configManager.getString(ApplicationConfig.IpApplicGare), configManager.getInt(ApplicationConfig.PortApplicDepotToApplicGare));

        eventManager.addListener(networkReceiver, new MessageHandler(this));
        eventManager.addListener(applicDepotFrame.getButtonMsgRecu(), new ButtonMsgRecuHandler(this));
        eventManager.addListener(applicDepotFrame.getButtonSignalArriveeTrain(), new ButtonSignalArriveeTrainHandler(this));
        eventManager.addListener(applicDepotFrame.getButtonValider(), new ButtonValiderHandler(applicDepotFrame));
        eventManager.addListener(applicDepotFrame.getComboBoxVoie(), new ComboBoxVoieHandler(applicDepotFrame));
        startThreads();
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
        networkSender.endSending();
    }

    public ApplicDepotFrame getFrame() {
        return applicDepotFrame;
    }

    public NetworkStringSender getNetworkSender() {
        return networkSender;
    }

    public static void main(String[] args) {
        new ApplicDepot();
    }

}
