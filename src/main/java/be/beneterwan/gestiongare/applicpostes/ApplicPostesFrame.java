package be.beneterwan.gestiongare.applicpostes;

import be.beneterwan.gestiongare.commons.ResourceManager;
import javax.swing.ImageIcon;

/**
 * @author bendem et Curlybear
 */
public class ApplicPostesFrame extends javax.swing.JFrame {

    public ApplicPostesFrame() {
        super("Applic Postes");
        initComponents();
        picture.setIcon(new ImageIcon(ResourceManager.getResourceFile("img/train-2.jpg")));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboBoxPostes = new javax.swing.JComboBox();
        buttonValider = new javax.swing.JButton();
        picturePanel = new javax.swing.JPanel();
        picture = new javax.swing.JLabel();
        labelAnnonce = new javax.swing.JLabel();
        fieldAnnonce = new javax.swing.JTextField();
        labelGare = new javax.swing.JLabel();
        buttonMsgRecu = new javax.swing.JButton();
        labelTrainConsidere = new javax.swing.JLabel();
        fieldTrainConsidere = new javax.swing.JTextField();
        buttonSignalPassageTrain = new javax.swing.JButton();
        labelTrainPartis = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaTrainPartis = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        comboBoxPostes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "In", "Out" }));

        buttonValider.setText("Valider");

        picturePanel.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout picturePanelLayout = new javax.swing.GroupLayout(picturePanel);
        picturePanel.setLayout(picturePanelLayout);
        picturePanelLayout.setHorizontalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picture, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
        );
        picturePanelLayout.setVerticalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picture, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );

        labelAnnonce.setText("Annonce:");

        labelGare.setText("Gare");

        buttonMsgRecu.setText("Message reçu");

        labelTrainConsidere.setText("Train considéré: ");

        buttonSignalPassageTrain.setText("Signaler passage train");

        labelTrainPartis.setText("Trains partis:");

        textAreaTrainPartis.setColumns(20);
        textAreaTrainPartis.setRows(5);
        jScrollPane1.setViewportView(textAreaTrainPartis);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelAnnonce)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelGare)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(buttonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(fieldAnnonce)
                                            .addComponent(buttonMsgRecu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                                        .addGap(10, 10, 10)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBoxPostes, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(picturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTrainConsidere)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldTrainConsidere))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSignalPassageTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelTrainPartis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(picturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxPostes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonValider))
                        .addGap(23, 23, 23)
                        .addComponent(labelGare)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelAnnonce)
                            .addComponent(fieldAnnonce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonMsgRecu)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTrainConsidere)
                    .addComponent(fieldTrainConsidere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonSignalPassageTrain)
                        .addComponent(labelTrainPartis))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonMsgRecu;
    private javax.swing.JButton buttonSignalPassageTrain;
    private javax.swing.JButton buttonValider;
    private javax.swing.JComboBox comboBoxPostes;
    private javax.swing.JTextField fieldAnnonce;
    private javax.swing.JTextField fieldTrainConsidere;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAnnonce;
    private javax.swing.JLabel labelGare;
    private javax.swing.JLabel labelTrainConsidere;
    private javax.swing.JLabel labelTrainPartis;
    private javax.swing.JLabel picture;
    private javax.swing.JPanel picturePanel;
    private javax.swing.JTextArea textAreaTrainPartis;
    // End of variables declaration//GEN-END:variables
}
