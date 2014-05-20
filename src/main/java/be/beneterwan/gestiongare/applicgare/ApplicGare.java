package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.applicgare.handlers.ControleInHandler;
import be.beneterwan.gestiongare.applicgare.handlers.ControleOutHandler;
import be.beneterwan.gestiongare.applicgare.handlers.DepotHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuAideAboutHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuAideDateHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuAideLogHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuIncidentEnregistrerHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuIncidentListHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuTrainFormationHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuTrainListHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuUtilisateurAddHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuUtilisateurListHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MenuUtilisateurLogHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MessageDepotHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MessagePostesInHandler;
import be.beneterwan.gestiongare.applicgare.handlers.MessagePostesOutHandler;
import be.beneterwan.gestiongare.applicgare.handlers.TableSelectionChangedHandler;
import be.beneterwan.gestiongare.applicgare.handlers.TrainSuivantHandler;
import be.beneterwan.gestiongare.applicgare.incidents.IncidentEvent;
import be.beneterwan.gestiongare.applicgare.incidents.ReportBean;
import be.beneterwan.gestiongare.commons.ApplicationConfig;
import be.beneterwan.gestiongare.commons.config.ConfigManager;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.commons.network.receiver.NetworkReceiver;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Logger;
import network.NetworkStringSender;

/**
 * @author bendem & Curlybear
 */
public class ApplicGare {

    private static final Logger LOGGER = new CustomLogger(ApplicGare.class);
    private static ApplicGareFrame frame;

    private final TrainManager trainManager;
    private ReportBean reportBean;
    private final ApplicGareEventManager eventManager;
    private final NetworkReceiver postesInNetworkReceiver;
    private final NetworkReceiver postesOutNetworkReceiver;
    private final NetworkReceiver depotNetworkReceiver;
    private final ConfigManager configManager;
    private NetworkStringSender postesInNetworkSender;
    private NetworkStringSender postesOutNetworkSender;
    private NetworkStringSender depotNetworkSender;

    private final LinkedList<IncidentEvent> listEvents = new LinkedList<>();
    private Set<HoraireTrain> horaires;

    public ApplicGare() {
        LOGGER.info("Starting up application...");

        // Config
        configManager = new ConfigManager(ConfigManager.CONFIG_FILE_NAME, true);

        // Preparing utilities
        postesInNetworkReceiver = new NetworkReceiver(configManager.getInt(ApplicationConfig.PortApplicInToApplicGare));
        postesOutNetworkReceiver = new NetworkReceiver(configManager.getInt(ApplicationConfig.PortApplicOutToApplicGare));
        depotNetworkReceiver = new NetworkReceiver(configManager.getInt(ApplicationConfig.PortApplicDepotToApplicGare));

        postesInNetworkReceiver.start();
        postesOutNetworkReceiver.start();
        depotNetworkReceiver.start();

        trainManager = new TrainManager(this);

        // Loading ui
        frame = new ApplicGareFrame(this);
        frame.setVisible(true);
        frame.setLoggedIn(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                Frame fenLogin = frame.getFenLogin();
                if(fenLogin != null) {
                    fenLogin.dispose();
                }
                stopUtilities();
                frame.dispose();
            }
        });

        // Setting up events
        eventManager = new ApplicGareEventManager();
        eventManager.addListener(frame.getMenuUtilisateurLog(), new MenuUtilisateurLogHandler(frame));
        eventManager.addListener(frame.getMenuAideAbout(), new MenuAideAboutHandler(frame));
        eventManager.addListener(frame.getMenuAideDate(), new MenuAideDateHandler(frame));
        eventManager.addListener(frame.getMenuUtilisateurListe(), new MenuUtilisateurListHandler(frame));
        eventManager.addListener(frame.getMenuUtilisateurNouvelUtilisateur(), new MenuUtilisateurAddHandler(frame));
        eventManager.addListener(frame.getMenuTrainListe(), new MenuTrainListHandler(frame));
        eventManager.addListener(frame.getButtonTrainSuivant(), new TrainSuivantHandler(this));
        eventManager.addListener(frame.getButtonControleIn(), new ControleInHandler(this));
        eventManager.addListener(frame.getButtonDepot(), new DepotHandler(this));
        eventManager.addListener(frame.getButtonControleOut(), new ControleOutHandler(this));
        eventManager.addListener(frame.getTableOccupationVoies().getSelectionModel(), new TableSelectionChangedHandler(this));
        eventManager.addListener(frame.getMenuIncidentsListe(), new MenuIncidentListHandler(frame));
        eventManager.addListener(frame.getMenuIncidentsEnregistrer(), new MenuIncidentEnregistrerHandler(this));
        eventManager.addListener(frame.getMenuTrainFormation(), new MenuTrainFormationHandler(this));
        eventManager.addListener(frame.getMenuAideAfficherLog(), new MenuAideLogHandler());

        // Opening login frame
        frame.openLoginFrame();

        eventManager.addListener(postesInNetworkReceiver, new MessagePostesInHandler(this));
        eventManager.addListener(postesOutNetworkReceiver, new MessagePostesOutHandler(this));
        eventManager.addListener(depotNetworkReceiver, new MessageDepotHandler(this));
    }

    public void startUtilities() {
        LOGGER.info("Starting utilities...");

        if(reportBean == null) {
            reportBean = new ReportBean(this);
        }

        if(postesInNetworkSender == null) {
            postesInNetworkSender = new NetworkStringSender(configManager.getString(ApplicationConfig.IpApplicIn), configManager.getInt(ApplicationConfig.PortApplicGareToApplicIn));
        }
        if(postesOutNetworkSender == null) {
            postesOutNetworkSender = new NetworkStringSender(configManager.getString(ApplicationConfig.IpApplicOut), configManager.getInt(ApplicationConfig.PortApplicGareToApplicOut));
        }
        if(depotNetworkSender == null) {
            depotNetworkSender = new NetworkStringSender(configManager.getString(ApplicationConfig.IpApplicDepot), configManager.getInt(ApplicationConfig.PortApplicGareToApplicDepot));
        }
    }

    public void stopUtilities() {
        LOGGER.info("Stopping utilities...");

        if(postesInNetworkReceiver.isRunning()) {
            postesInNetworkReceiver.stop();
        }
        if(postesOutNetworkReceiver.isRunning()) {
            postesOutNetworkReceiver.stop();
        }
        if(depotNetworkReceiver.isRunning()) {
            depotNetworkReceiver.stop();
        }

        if(postesInNetworkSender != null) {
            postesInNetworkSender.endSending();
        }
        if(postesOutNetworkSender != null) {
            postesOutNetworkSender.endSending();
        }
        if(depotNetworkSender != null) {
            depotNetworkSender.endSending();
        }

        if(reportBean != null) {
            reportBean.kill();
        }

        LOGGER.fine("Utilities stopped");
    }

    public void shitHappened(IncidentEvent shit) {
        listEvents.add(shit);
        frame.openIncidentDialog(shit.getIncidentMessage());
    }

    public void shitIsBetter() {
        reportBean.reset();
    }

    public Set<HoraireTrain> getHoraires() {
        return horaires;
    }

    public ApplicGareEventManager getEventManager() {
        return eventManager;
    }

    public ApplicGareFrame getFrame() {
        return frame;
    }

    public TrainManager getTrainManager() {
        return trainManager;
    }

    public NetworkStringSender getPostesInNetworkSender() {
        return postesInNetworkSender;
    }

    public NetworkStringSender getPostesOutNetworkSender() {
        return postesOutNetworkSender;
    }

    public NetworkStringSender getDepotNetworkSender() {
        return depotNetworkSender;
    }

    public LinkedList<IncidentEvent> getListEvents() {
        return listEvents;
    }

    public static void main(String[] args) {
        new ApplicGare();
    }

}
