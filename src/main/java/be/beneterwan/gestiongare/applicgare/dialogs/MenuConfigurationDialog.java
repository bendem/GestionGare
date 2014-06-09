package be.beneterwan.gestiongare.applicgare.dialogs;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.commons.ApplicationConfig;
import be.beneterwan.gestiongare.commons.config.ConfigManager;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.eventmanagement.EventManager;
import java.awt.Component;
import java.util.EventObject;
import javax.swing.JOptionPane;

/**
 * @author bendem & Curlybear
 */
public class MenuConfigurationDialog extends javax.swing.JDialog {

    private final EventManager eventManager;
    private final ConfigManager config;

    public MenuConfigurationDialog(ApplicGareFrame parent) {
        super(parent, true);
        initComponents();

        config = parent.getApplicGare().getConfigManager();
        textAreaMessageGreve.setText(config.getString(ApplicationConfig.MessageIncidentGreve));
        textAreaMessageManifestation.setText(config.getString(ApplicationConfig.MessageIncidentManifestation));
        textAreaMessageRetard.setText(config.getString(ApplicationConfig.MessageIncidentRetard));
        textFieldIPApplicDepot.setText(config.getString(ApplicationConfig.IpApplicDepot));
        textFieldIPApplicGare.setText(config.getString(ApplicationConfig.IpApplicGare));
        textFieldIPApplicPosteIn.setText(config.getString(ApplicationConfig.IpApplicIn));
        textFieldIPApplicPosteOut.setText(config.getString(ApplicationConfig.IpApplicOut));
        textFieldPortApplicDepot.setText(config.getString(ApplicationConfig.PortApplicGareToApplicDepot));
        textFieldPortApplicPosteIn.setText(config.getString(ApplicationConfig.PortApplicGareToApplicIn));
        textFieldPortApplicPosteOut.setText(config.getString(ApplicationConfig.PortApplicGareToApplicOut));
        textFieldPortDepotToGare.setText(config.getString(ApplicationConfig.PortApplicDepotToApplicGare));
        textFieldPortPosteInToGare.setText(config.getString(ApplicationConfig.PortApplicInToApplicGare));
        textFieldPortPosteOutToGare.setText(config.getString(ApplicationConfig.PortApplicOutToApplicGare));

        eventManager = new EventManager();
        eventManager.addListener(buttonOK, new EventHandler() {
            @Override
            public void execute(EventObject event) {
                if(save()) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog((Component) event.getSource(), "Unable to save, check the fields");
                }
            }
        });
        eventManager.addListener(buttonAnnuler, new EventHandler() {
            @Override
            public void execute(EventObject event) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean save() {
        if(!isInt(textFieldPortApplicDepot.getText())) {
            return false;
        }
        if(!isInt(textFieldPortApplicPosteIn.getText())) {
            return false;
        }
        if(!isInt(textFieldPortDepotToGare.getText())) {
            return false;
        }
        if(!isInt(textFieldPortApplicPosteOut.getText())) {
            return false;
        }
        if(!isInt(textFieldPortPosteInToGare.getText())) {
            return false;
        }
        if(!isInt(textFieldPortPosteOutToGare.getText())) {
            return false;
        }
        config.set(ApplicationConfig.MessageIncidentGreve, textAreaMessageGreve.getText());
        config.set(ApplicationConfig.MessageIncidentManifestation, textAreaMessageManifestation.getText());
        config.set(ApplicationConfig.MessageIncidentRetard, textAreaMessageRetard.getText());
        config.set(ApplicationConfig.IpApplicDepot, textFieldIPApplicDepot.getText());
        config.set(ApplicationConfig.IpApplicGare, textFieldIPApplicGare.getText());
        config.set(ApplicationConfig.IpApplicIn, textFieldIPApplicPosteIn.getText());
        config.set(ApplicationConfig.IpApplicOut, textFieldIPApplicPosteOut.getText());
        config.set(ApplicationConfig.PortApplicGareToApplicDepot, textFieldPortApplicDepot.getText());
        config.set(ApplicationConfig.PortApplicGareToApplicIn, textFieldPortApplicPosteIn.getText());
        config.set(ApplicationConfig.PortApplicGareToApplicOut, textFieldPortApplicPosteOut.getText());
        config.set(ApplicationConfig.PortApplicDepotToApplicGare, textFieldPortDepotToGare.getText());
        config.set(ApplicationConfig.PortApplicInToApplicGare, textFieldPortPosteInToGare.getText());
        config.set(ApplicationConfig.PortApplicOutToApplicGare, textFieldPortPosteOutToGare.getText());
        return config.save();
    }

    private boolean isInt(String toCheck) {
        try {
            Integer.parseInt(toCheck);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        labelIPApplicDepot = new javax.swing.JLabel();
        labelIPApplicPosteIn = new javax.swing.JLabel();
        labelIPApplicPosteOut = new javax.swing.JLabel();
        labelIPApplicGare = new javax.swing.JLabel();
        labelPortPosteInToGare = new javax.swing.JLabel();
        labelPortPosteOutToGare = new javax.swing.JLabel();
        labelPortApplicDepot = new javax.swing.JLabel();
        labelPortApplicPosteIn = new javax.swing.JLabel();
        labelPortApplicPosteOut = new javax.swing.JLabel();
        labelPortDepotToGare = new javax.swing.JLabel();
        textFieldIPApplicDepot = new javax.swing.JTextField();
        textFieldIPApplicPosteIn = new javax.swing.JTextField();
        textFieldIPApplicPosteOut = new javax.swing.JTextField();
        textFieldIPApplicGare = new javax.swing.JTextField();
        textFieldPortApplicDepot = new javax.swing.JTextField();
        textFieldPortApplicPosteIn = new javax.swing.JTextField();
        textFieldPortApplicPosteOut = new javax.swing.JTextField();
        textFieldPortPosteInToGare = new javax.swing.JTextField();
        textFieldPortPosteOutToGare = new javax.swing.JTextField();
        textFieldPortDepotToGare = new javax.swing.JTextField();
        labelMessageRetard = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaMessageRetard = new javax.swing.JTextArea();
        labelMessageManifestation = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaMessageManifestation = new javax.swing.JTextArea();
        labelMessageGreve = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaMessageGreve = new javax.swing.JTextArea();
        buttonOK = new javax.swing.JButton();
        buttonAnnuler = new javax.swing.JButton();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelIPApplicDepot.setText("IP ApplicDepot :");

        labelIPApplicPosteIn.setText("IP ApplicPosteIn :");

        labelIPApplicPosteOut.setText("IP ApplicPosteOut :");

        labelIPApplicGare.setText("IP ApplicGare :");

        labelPortPosteInToGare.setText("Port d'écoute d'ApplicPosteIn :");

        labelPortPosteOutToGare.setText("Port d'écoute d'ApplicPosteOut :");

        labelPortApplicDepot.setText("Port ApplicDepot :");

        labelPortApplicPosteIn.setText("Port ApplicPosteIn :");

        labelPortApplicPosteOut.setText("Port ApplicPosteOut :");

        labelPortDepotToGare.setText("Port d'écoute d'ApplicDepot :");

        labelMessageRetard.setText("Message de retard :");

        textAreaMessageRetard.setColumns(20);
        textAreaMessageRetard.setRows(5);
        jScrollPane1.setViewportView(textAreaMessageRetard);

        labelMessageManifestation.setText("Message de manifestation :");

        textAreaMessageManifestation.setColumns(20);
        textAreaMessageManifestation.setRows(5);
        jScrollPane2.setViewportView(textAreaMessageManifestation);

        labelMessageGreve.setText("Message de grève :");

        textAreaMessageGreve.setColumns(20);
        textAreaMessageGreve.setRows(5);
        jScrollPane3.setViewportView(textAreaMessageGreve);

        buttonOK.setText("OK");

        buttonAnnuler.setText("Annuler");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelMessageRetard)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMessageManifestation)
                            .addComponent(labelMessageGreve))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelIPApplicPosteOut)
                                            .addComponent(labelIPApplicPosteIn)
                                            .addComponent(labelIPApplicDepot))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textFieldIPApplicPosteOut)
                                            .addComponent(textFieldIPApplicPosteIn, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(textFieldIPApplicDepot, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelIPApplicGare)
                                        .addGap(40, 40, 40)
                                        .addComponent(textFieldIPApplicGare)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelPortApplicPosteIn)
                                            .addComponent(labelPortApplicDepot)
                                            .addComponent(labelPortApplicPosteOut)
                                            .addComponent(labelPortDepotToGare))
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textFieldPortApplicPosteIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                            .addComponent(textFieldPortApplicPosteOut, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(textFieldPortDepotToGare)
                                            .addComponent(textFieldPortApplicDepot)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelPortPosteOutToGare)
                                            .addComponent(labelPortPosteInToGare))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(textFieldPortPosteInToGare, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                            .addComponent(textFieldPortPosteOutToGare))))))
                        .addGap(23, 23, 23))))
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163)
                .addComponent(buttonAnnuler)
                .addGap(0, 136, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIPApplicDepot)
                    .addComponent(labelPortApplicDepot)
                    .addComponent(textFieldIPApplicDepot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldPortApplicDepot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIPApplicPosteIn)
                    .addComponent(labelPortApplicPosteIn)
                    .addComponent(textFieldIPApplicPosteIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldPortApplicPosteIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIPApplicPosteOut)
                    .addComponent(labelPortApplicPosteOut)
                    .addComponent(textFieldIPApplicPosteOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldPortApplicPosteOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIPApplicGare)
                    .addComponent(labelPortDepotToGare)
                    .addComponent(textFieldIPApplicGare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldPortDepotToGare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPortPosteInToGare)
                    .addComponent(textFieldPortPosteInToGare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPortPosteOutToGare)
                    .addComponent(textFieldPortPosteOutToGare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(labelMessageRetard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelMessageManifestation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelMessageGreve)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOK)
                    .addComponent(buttonAnnuler))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAnnuler;
    private javax.swing.JButton buttonOK;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelIPApplicDepot;
    private javax.swing.JLabel labelIPApplicGare;
    private javax.swing.JLabel labelIPApplicPosteIn;
    private javax.swing.JLabel labelIPApplicPosteOut;
    private javax.swing.JLabel labelMessageGreve;
    private javax.swing.JLabel labelMessageManifestation;
    private javax.swing.JLabel labelMessageRetard;
    private javax.swing.JLabel labelPortApplicDepot;
    private javax.swing.JLabel labelPortApplicPosteIn;
    private javax.swing.JLabel labelPortApplicPosteOut;
    private javax.swing.JLabel labelPortDepotToGare;
    private javax.swing.JLabel labelPortPosteInToGare;
    private javax.swing.JLabel labelPortPosteOutToGare;
    private javax.swing.JTextArea textAreaMessageGreve;
    private javax.swing.JTextArea textAreaMessageManifestation;
    private javax.swing.JTextArea textAreaMessageRetard;
    private javax.swing.JTextField textFieldIPApplicDepot;
    private javax.swing.JTextField textFieldIPApplicGare;
    private javax.swing.JTextField textFieldIPApplicPosteIn;
    private javax.swing.JTextField textFieldIPApplicPosteOut;
    private javax.swing.JTextField textFieldPortApplicDepot;
    private javax.swing.JTextField textFieldPortApplicPosteIn;
    private javax.swing.JTextField textFieldPortApplicPosteOut;
    private javax.swing.JTextField textFieldPortDepotToGare;
    private javax.swing.JTextField textFieldPortPosteInToGare;
    private javax.swing.JTextField textFieldPortPosteOutToGare;
    // End of variables declaration//GEN-END:variables
}
