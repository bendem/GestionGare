package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.applicgare.handlers.LoginHandler;
import be.beneterwan.gestiongare.authenticate.User;
import be.beneterwan.gestiongare.commons.ResourceManager;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.logins.LoginFrame;
import java.awt.Frame;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

/**
 * @author bendem & Curlybear
 */
public class ApplicGareFrame extends javax.swing.JFrame {

    public static final Logger LOGGER = new CustomLogger(ApplicGareFrame.class);

    private final ApplicGare applicGare;
    private boolean loggedIn = false;
    private User currentUser;
    private DateFormat dateFormat;
    private LoginFrame fenLogin;

    private final Map<String, JDialog> dialogs;

    public ApplicGareFrame(ApplicGare applicController) {
        super("== ApplicGare ==");
        LOGGER.info("Building window...");
        this.applicGare = applicController;
        dialogs = new HashMap<>();
        initComponents();

        picture.setIcon(new ImageIcon(ResourceManager.getResourceFile("img/train-1.jpg")));

        // Setting default windows params
        dateFormat = new DateFormat();

        // Setting up table column sizes
        TableColumnModel columnModel = tableOccupationVoies.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(70);
        columnModel.getColumn(2).setPreferredWidth(330);
        columnModel.getColumn(3).setPreferredWidth(330);
        columnModel.getColumn(4).setPreferredWidth(74);
        columnModel.getColumn(5).setPreferredWidth(60);

        // Packing windows to fit constructor changes
        pack();
        setLocationRelativeTo(null);
        LOGGER.fine("Window built");
    }

    public void openDialog(Class<? extends JDialog> dialogClass, Object...params) {
        JDialog diag = dialogs.get(dialogClass.getName());
        if(diag != null && diag.isVisible()) {
            LOGGER.fine("Dialog already opened");
            diag.requestFocus();
            return;
        }
        Class<?>[] paramTypes = new Class<?>[params.length];
        for(int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
        }
        Constructor<? extends JDialog> ctor;
        try {
            ctor = dialogClass.getConstructor(paramTypes);
        } catch(NoSuchMethodException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, "No corresponding constructor", ex);
            return;
        }
        try {
            LOGGER.fine("Opening " + dialogClass.getSimpleName() + "...");
            dialogs.put(dialogClass.getName(), ctor.newInstance(params));
        } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void closeDialog(Class<? extends JDialog> dialogClass) {
        JDialog diag = dialogs.get(dialogClass.getName());
        if(diag != null) {
            LOGGER.fine("Closing " + dialogClass.getSimpleName() + "...");
            diag.dispose();
            dialogs.remove(dialogClass.getName());
        } else {
            LOGGER.severe("Can't close not opened dialog!");
        }
    }

    @Override
    public void dispose() {
        for(Map.Entry<String, JDialog> entry : dialogs.entrySet()) {
            JDialog jDialog = entry.getValue();
            if(jDialog != null) {
                jDialog.dispose();
            }
        }
        super.dispose();
    }

    public void openLoginFrame() {
        LOGGER.info("Opening Login window...");
        fenLogin = new LoginFrame();
        applicGare.getEventManager().addListener(fenLogin, new LoginHandler(this));
        fenLogin.requestFocusInWindow();
        LOGGER.fine("Login window opened.");
    }

    private void lockInterface() {
        labelResponsableGare.setText("Vous n'êtes pas connecté!");
        menuUtilisateurLog.setText("Login");
        changeInterfaceEnableState(false);
    }

    private void unlockInterface(String login) {
        labelResponsableGare.setText("Responsable gare : " + login);
        menuUtilisateurLog.setText("Logout");
        // TODO Add stuff to tableOccupationVoies and comboBoxTrain
        changeInterfaceEnableState(true);
        applicGare.startUtilities();
    }

    private void changeInterfaceEnableState(boolean enableState) {
        menuTrains.setEnabled(enableState);
        menuIncidents.setEnabled(enableState);
        menuConfiguration.setEnabled(enableState);
        if(!enableState || (enableState && currentUser.isAdmin())) {
            menuUtilisateurListe.setEnabled(enableState);
            menuUtilisateurNouvelUtilisateur.setEnabled(enableState);
        }
        comboBoxTrain.setEnabled(enableState);
        fieldProchainTrain.setEnabled(enableState);
        buttonTrainSuivant.setEnabled(enableState);
        buttonControleIn.setEnabled(false);
        buttonControleOut.setEnabled(false);
        buttonDepot.setEnabled(false);
        fieldControleIn.setEnabled(enableState);
        fieldControleOut.setEnabled(enableState);
        fieldProchainTrain.setEnabled(enableState);
        tableOccupationVoies.setEnabled(enableState);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public ApplicGare getApplicGare() {
        return applicGare;
    }

    public Frame getFenLogin() {
        return fenLogin;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public JMenuItem getMenuAideAbout() {
        return menuAideAbout;
    }

    public JMenuItem getMenuAideAfficherLog() {
        return menuAideAfficherLog;
    }

    public JMenuItem getMenuAideDate() {
        return menuAideDate;
    }

    public JMenuItem getMenuConfigurationParametreReseau() {
        return menuConfigurationParametreReseau;
    }

    public JMenuItem getMenuConfigurationReglageTemps() {
        return menuConfigurationReglageTemps;
    }

    public JMenuItem getMenuIncidentsEnregistrer() {
        return menuIncidentsEnregistrer;
    }

    public JMenuItem getMenuIncidentsListe() {
        return menuIncidentsListe;
    }

    public JMenuItem getMenuTrainFormation() {
        return menuTrainFormation;
    }

    public JMenuItem getMenuTrainListe() {
        return menuTrainListe;
    }

    public JMenuItem getMenuUtilisateurListe() {
        return menuUtilisateurListe;
    }

    public JMenuItem getMenuUtilisateurLog() {
        return menuUtilisateurLog;
    }

    public JMenuItem getMenuUtilisateurNouvelUtilisateur() {
        return menuUtilisateurNouvelUtilisateur;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public JButton getButtonTrainSuivant() {
        return buttonTrainSuivant;
    }

    public JTextField getFieldProchainTrain() {
        return fieldProchainTrain;
    }

    public JButton getButtonControleIn() {
        return buttonControleIn;
    }

    public JButton getButtonControleOut() {
        return buttonControleOut;
    }

    public JButton getButtonDepot() {
        return buttonDepot;
    }

    public JTextField getFieldControleIn() {
        return fieldControleIn;
    }

    public JTextField getFieldControleOut() {
        return fieldControleOut;
    }

    public JTextField getFieldDepot() {
        return fieldDepot;
    }

    public JTable getTableOccupationVoies() {
        return tableOccupationVoies;
    }

    public JComboBox getComboBoxTrain() {
        return comboBoxTrain;
    }

    public void setLoggedIn(User user) {
        this.currentUser = user;
        loggedIn = user != null;
        if(loggedIn) {
            unlockInterface(user.getLogin());
        } else {
            lockInterface();
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelResponsableGare = new javax.swing.JLabel();
        labelTrainArrivesRepartis = new javax.swing.JLabel();
        comboBoxTrain = new javax.swing.JComboBox<>();
        labelProchainTrain = new javax.swing.JLabel();
        fieldProchainTrain = new javax.swing.JTextField();
        buttonTrainSuivant = new javax.swing.JButton();
        buttonControleIn = new javax.swing.JButton();
        buttonDepot = new javax.swing.JButton();
        buttonControleOut = new javax.swing.JButton();
        picturePanel = new javax.swing.JPanel();
        picture = new javax.swing.JLabel();
        scrollPaneTable = new javax.swing.JScrollPane();
        tableOccupationVoies = new javax.swing.JTable();
        labelReponseControleIn = new javax.swing.JLabel();
        fieldControleIn = new javax.swing.JTextField();
        labelControleIn = new javax.swing.JLabel();
        labelReponseDepot = new javax.swing.JLabel();
        fieldDepot = new javax.swing.JTextField();
        labelDepot = new javax.swing.JLabel();
        labelControleOut = new javax.swing.JLabel();
        fieldControleOut = new javax.swing.JTextField();
        labelReponseControleOut = new javax.swing.JLabel();
        labelOccupationVoies = new javax.swing.JLabel();
        topMenuBar = new javax.swing.JMenuBar();
        menuUtilisateur = new javax.swing.JMenu();
        menuUtilisateurLog = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuUtilisateurNouvelUtilisateur = new javax.swing.JMenuItem();
        menuUtilisateurListe = new javax.swing.JMenuItem();
        menuTrains = new javax.swing.JMenu();
        menuTrainListe = new javax.swing.JMenuItem();
        menuTrainFormation = new javax.swing.JMenuItem();
        menuIncidents = new javax.swing.JMenu();
        menuIncidentsListe = new javax.swing.JMenuItem();
        menuIncidentsEnregistrer = new javax.swing.JMenuItem();
        menuConfiguration = new javax.swing.JMenu();
        menuConfigurationParametreReseau = new javax.swing.JMenuItem();
        menuConfigurationReglageTemps = new javax.swing.JMenuItem();
        menuAide = new javax.swing.JMenu();
        menuAideDate = new javax.swing.JMenuItem();
        menuAideAfficherLog = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuAideAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        labelResponsableGare.setText("Responsable gare :");

        labelTrainArrivesRepartis.setText("Trains arrivés et repartis : ");

        comboBoxTrain.setModel(new TrainGoneComboBoxModel(getApplicGare().getTrainManager().getOutboundTrains()));

        labelProchainTrain.setText("Prochain train :");

        fieldProchainTrain.setEditable(false);

        buttonTrainSuivant.setText("Train suivant");

        buttonControleIn.setText("Prévenir poste contrôle in");

        buttonDepot.setText("Prévenir hangar");

        buttonControleOut.setText("Prévenir poste contrôle out");

        picturePanel.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout picturePanelLayout = new javax.swing.GroupLayout(picturePanel);
        picturePanel.setLayout(picturePanelLayout);
        picturePanelLayout.setHorizontalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        picturePanelLayout.setVerticalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picture, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
        );

        tableOccupationVoies.setModel(new OccupationVoiesTableModel(applicGare));
        tableOccupationVoies.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableOccupationVoies.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableOccupationVoies.getTableHeader().setReorderingAllowed(false);
        scrollPaneTable.setViewportView(tableOccupationVoies);

        labelReponseControleIn.setText("Réponse :");

        fieldControleIn.setEditable(false);

        labelControleIn.setText("Contrôle in");

        labelReponseDepot.setText("Réponse :");

        fieldDepot.setEditable(false);

        labelDepot.setText("Depot");

        labelControleOut.setText("Contrôle out");

        fieldControleOut.setEditable(false);

        labelReponseControleOut.setText("Réponse :");

        labelOccupationVoies.setText("Occupation des voies :");

        topMenuBar.setBackground(new java.awt.Color(153, 153, 153));
        topMenuBar.setBorder(null);

        menuUtilisateur.setText("Utilisateurs");

        menuUtilisateurLog.setText("Logout");
        menuUtilisateur.add(menuUtilisateurLog);
        menuUtilisateur.add(jSeparator2);

        menuUtilisateurNouvelUtilisateur.setText("Nouvel utilisateur");
        menuUtilisateur.add(menuUtilisateurNouvelUtilisateur);

        menuUtilisateurListe.setText("Liste");
        menuUtilisateur.add(menuUtilisateurListe);

        topMenuBar.add(menuUtilisateur);

        menuTrains.setText("Trains");

        menuTrainListe.setText("Liste du jour");
        menuTrains.add(menuTrainListe);

        menuTrainFormation.setText("Formation");
        menuTrains.add(menuTrainFormation);

        topMenuBar.add(menuTrains);

        menuIncidents.setText("Incidents");

        menuIncidentsListe.setText("Liste");
        menuIncidents.add(menuIncidentsListe);

        menuIncidentsEnregistrer.setText("Enregistrer");
        menuIncidents.add(menuIncidentsEnregistrer);

        topMenuBar.add(menuIncidents);

        menuConfiguration.setText("Configuration");

        menuConfigurationParametreReseau.setText("Paramètres réseaux");
        menuConfiguration.add(menuConfigurationParametreReseau);

        menuConfigurationReglageTemps.setText("Réglages des temps");
        menuConfiguration.add(menuConfigurationReglageTemps);

        topMenuBar.add(menuConfiguration);

        menuAide.setText("Aide");

        menuAideDate.setText("Date format");
        menuAide.add(menuAideDate);

        menuAideAfficherLog.setText("Afficher log");
        menuAide.add(menuAideAfficherLog);
        menuAide.add(jSeparator1);

        menuAideAbout.setText("A propos");
        menuAide.add(menuAideAbout);

        topMenuBar.add(menuAide);

        setJMenuBar(topMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelProchainTrain)
                                .addGap(508, 508, 508))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(comboBoxTrain, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelResponsableGare, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelTrainArrivesRepartis, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fieldProchainTrain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonTrainSuivant)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(picturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(scrollPaneTable)
                    .addComponent(labelOccupationVoies)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonControleIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelReponseControleIn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelControleIn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fieldControleIn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelReponseDepot)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDepot)
                                    .addComponent(fieldDepot)))
                            .addComponent(buttonDepot, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelReponseControleOut)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelControleOut)
                                    .addComponent(fieldControleOut)))
                            .addComponent(buttonControleOut, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelResponsableGare)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelTrainArrivesRepartis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxTrain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelProchainTrain)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldProchainTrain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonTrainSuivant)))
                    .addComponent(picturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonControleIn)
                    .addComponent(buttonDepot)
                    .addComponent(buttonControleOut))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDepot)
                            .addComponent(labelControleIn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldControleIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelReponseDepot)
                            .addComponent(fieldDepot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelReponseControleIn)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelControleOut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelReponseControleOut)
                            .addComponent(fieldControleOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42)
                .addComponent(labelOccupationVoies)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonControleIn;
    private javax.swing.JButton buttonControleOut;
    private javax.swing.JButton buttonDepot;
    private javax.swing.JButton buttonTrainSuivant;
    private javax.swing.JComboBox comboBoxTrain;
    private javax.swing.JTextField fieldControleIn;
    private javax.swing.JTextField fieldControleOut;
    private javax.swing.JTextField fieldDepot;
    private javax.swing.JTextField fieldProchainTrain;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel labelControleIn;
    private javax.swing.JLabel labelControleOut;
    private javax.swing.JLabel labelDepot;
    private javax.swing.JLabel labelOccupationVoies;
    private javax.swing.JLabel labelProchainTrain;
    private javax.swing.JLabel labelReponseControleIn;
    private javax.swing.JLabel labelReponseControleOut;
    private javax.swing.JLabel labelReponseDepot;
    private javax.swing.JLabel labelResponsableGare;
    private javax.swing.JLabel labelTrainArrivesRepartis;
    private javax.swing.JMenu menuAide;
    private javax.swing.JMenuItem menuAideAbout;
    private javax.swing.JMenuItem menuAideAfficherLog;
    private javax.swing.JMenuItem menuAideDate;
    private javax.swing.JMenu menuConfiguration;
    private javax.swing.JMenuItem menuConfigurationParametreReseau;
    private javax.swing.JMenuItem menuConfigurationReglageTemps;
    private javax.swing.JMenu menuIncidents;
    private javax.swing.JMenuItem menuIncidentsEnregistrer;
    private javax.swing.JMenuItem menuIncidentsListe;
    private javax.swing.JMenuItem menuTrainFormation;
    private javax.swing.JMenuItem menuTrainListe;
    private javax.swing.JMenu menuTrains;
    private javax.swing.JMenu menuUtilisateur;
    private javax.swing.JMenuItem menuUtilisateurListe;
    private javax.swing.JMenuItem menuUtilisateurLog;
    private javax.swing.JMenuItem menuUtilisateurNouvelUtilisateur;
    private javax.swing.JLabel picture;
    private javax.swing.JPanel picturePanel;
    private javax.swing.JScrollPane scrollPaneTable;
    private javax.swing.JTable tableOccupationVoies;
    private javax.swing.JMenuBar topMenuBar;
    // End of variables declaration//GEN-END:variables
}
