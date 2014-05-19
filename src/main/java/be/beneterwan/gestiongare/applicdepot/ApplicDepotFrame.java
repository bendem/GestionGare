package be.beneterwan.gestiongare.applicdepot;

import be.beneterwan.gestiongare.commons.ResourceManager;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @author bendem & Curlybear
 */
public class ApplicDepotFrame extends javax.swing.JFrame {

    public static final int NB_VOIES = 4;
    private final ApplicDepot applicDepot;
    private HoraireTrain trainAnnonce;
    private final Queue<HoraireTrain> trainConsidere;
    private final Map<Integer, HoraireTrain> storedTrains;


    public ApplicDepotFrame(ApplicDepot applicController) {
        super("Applic Dépot");
        applicDepot = applicController;
        trainConsidere = new LinkedList<>();
        storedTrains = new HashMap<>();
        initTable();
        initComponents();
        picture.setIcon(new ImageIcon(ResourceManager.getResourceFile("img/train-3.jpg")));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JTextField getFieldAnnonce() {
        return fieldAnnonce;
    }

    public JButton getButtonSignalArriveeTrain() {
        return buttonSignalArriveeTrain;
    }

    public JButton getButtonMsgRecu() {
        return buttonMsgRecu;
    }

    public JTextField getFieldTrainConsidere() {
        return fieldTrainConsidere;
    }

    public HoraireTrain getTrainAnnonce() {
        return trainAnnonce;
    }

    public void setTrainAnnonce(HoraireTrain trainAnnonce) {
        this.trainAnnonce = trainAnnonce;
    }

    public HoraireTrain getTrainConsidere() {
        return trainConsidere.peek();
    }

    public Map<Integer, HoraireTrain> getStoredTrains() {
        return storedTrains;
    }

    public JButton getButtonValider() {
        return buttonValider;
    }

    public JComboBox getComboBoxVoie() {
        return comboBoxVoie;
    }

    public JTable getTableOccupationHangar() {
        return tableOccupationHangar;
    }

    public void addTrainConsidere(HoraireTrain trainConsidere) {
        this.trainConsidere.add(trainConsidere);
    }

    public HoraireTrain withdrawTrainConsidere() {
        return trainConsidere.poll();
    }

    private void initTable() {
        for(int i = NB_VOIES; i > 0; --i) {
            storedTrains.put(i, null);
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTrainConsidere = new javax.swing.JLabel();
        fieldTrainConsidere = new javax.swing.JTextField();
        picturePanel = new javax.swing.JPanel();
        picture = new javax.swing.JLabel();
        buttonMsgRecu = new javax.swing.JButton();
        labelAnnonce = new javax.swing.JLabel();
        fieldAnnonce = new javax.swing.JTextField();
        labelGare = new javax.swing.JLabel();
        buttonValider = new javax.swing.JButton();
        buttonSignalArriveeTrain = new javax.swing.JButton();
        labelVoie = new javax.swing.JLabel();
        comboBoxVoie = new javax.swing.JComboBox();
        labelOccupationHangar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOccupationHangar = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        labelTrainConsidere.setText("Train considéré:");

        picturePanel.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout picturePanelLayout = new javax.swing.GroupLayout(picturePanel);
        picturePanel.setLayout(picturePanelLayout);
        picturePanelLayout.setHorizontalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picture, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );
        picturePanelLayout.setVerticalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picture, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        );

        buttonMsgRecu.setText("Message reçu");
        buttonMsgRecu.setEnabled(false);

        labelAnnonce.setText("Annonce:");

        labelGare.setText("Gare");

        buttonValider.setText("Valider");
        buttonValider.setEnabled(false);

        buttonSignalArriveeTrain.setText("Signaler arrivée du train");
        buttonSignalArriveeTrain.setEnabled(false);

        labelVoie.setText("Voie choisie:");

        comboBoxVoie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        comboBoxVoie.setEnabled(false);

        labelOccupationHangar.setText("Occupation du hangar:");

        tableOccupationHangar.setModel(new OccupationHangarTableModel(this));
        jScrollPane1.setViewportView(tableOccupationHangar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSignalArriveeTrain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonMsgRecu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelAnnonce)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelTrainConsidere)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(fieldTrainConsidere))
                                    .addComponent(labelGare)))
                            .addComponent(fieldAnnonce, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(picturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelVoie)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxVoie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonValider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelOccupationHangar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(picturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(labelAnnonce)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldAnnonce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonMsgRecu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelGare)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTrainConsidere)
                            .addComponent(fieldTrainConsidere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonValider)
                    .addComponent(buttonSignalArriveeTrain)
                    .addComponent(labelVoie)
                    .addComponent(comboBoxVoie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelOccupationHangar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonMsgRecu;
    private javax.swing.JButton buttonSignalArriveeTrain;
    private javax.swing.JButton buttonValider;
    private javax.swing.JComboBox comboBoxVoie;
    private javax.swing.JTextField fieldAnnonce;
    private javax.swing.JTextField fieldTrainConsidere;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAnnonce;
    private javax.swing.JLabel labelGare;
    private javax.swing.JLabel labelOccupationHangar;
    private javax.swing.JLabel labelTrainConsidere;
    private javax.swing.JLabel labelVoie;
    private javax.swing.JLabel picture;
    private javax.swing.JPanel picturePanel;
    private javax.swing.JTable tableOccupationHangar;
    // End of variables declaration//GEN-END:variables
}
