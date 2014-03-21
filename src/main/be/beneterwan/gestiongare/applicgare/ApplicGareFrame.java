package be.beneterwan.gestiongare.applicgare;

import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * @author bendem
 */
public class ApplicGareFrame extends JFrame {

    /**
     * Creates new form ApplicGareFrame
     */
    public ApplicGareFrame() {
        initComponents();
        Logger.getLogger(ApplicGareFrame.class.getName()).info(ApplicGare.getRessourceFile("train.jpg").getAbsolutePath());
        //Icon image = new ImageIcon();
        //trainPicture.setIcon(image);
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

        topPanel = new javax.swing.JPanel();
        responsable = new javax.swing.JLabel();
        trainPicture = new javax.swing.JLabel();
        nextTrainPanel = new javax.swing.JPanel();
        notifyButtonsPanel = new javax.swing.JPanel();
        answerPanel = new javax.swing.JPanel();
        railwayOccupationTablePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(5, 1));

        topPanel.setLayout(new java.awt.CardLayout());

        responsable.setText("Responsable gare");
        topPanel.add(responsable, "card2");

        trainPicture.setText("Image");
        trainPicture.setAlignmentY(0.0F);
        topPanel.add(trainPicture, "card3");

        getContentPane().add(topPanel);

        nextTrainPanel.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(nextTrainPanel);

        notifyButtonsPanel.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(notifyButtonsPanel);

        answerPanel.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(answerPanel);

        railwayOccupationTablePanel.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(railwayOccupationTablePanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplicGareFrame.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApplicGareFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JPanel answerPanel;
    protected javax.swing.JPanel nextTrainPanel;
    protected javax.swing.JPanel notifyButtonsPanel;
    protected javax.swing.JPanel railwayOccupationTablePanel;
    protected javax.swing.JLabel responsable;
    protected javax.swing.JPanel topPanel;
    protected javax.swing.JLabel trainPicture;
    // End of variables declaration//GEN-END:variables
}