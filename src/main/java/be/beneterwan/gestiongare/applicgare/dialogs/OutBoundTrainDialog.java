
package be.beneterwan.gestiongare.applicgare.dialogs;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.commons.trains.HoraireTrain;
import be.beneterwan.gestiongare.commons.trains.Train;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bear
 */
public class OutBoundTrainDialog extends javax.swing.JDialog {

    public OutBoundTrainDialog(ApplicGareFrame frame) {
        super(frame, "Liste des trains partis", true);
        initComponents();

        // Loading train list
        List<HoraireTrain> list = frame.getApplicGare().getTrainManager().getOutboundTrains();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Numero");
        model.addColumn("Wagons");
        model.addColumn("Origine");
        model.addColumn("Destination");
        model.addColumn("Arrivée");
        model.addColumn("Départ");
        for(HoraireTrain horaire : list) {
            if(horaire == null) {
                continue;
            }
            Train train = horaire.getTrain();
            model.addRow(new Object[] {
                train.getType().name() + train.getNumero(),
                train.getWagons().size(),
                horaire.getOrigine(),
                horaire.getDestination(),
                String.format("%02d:%02d", horaire.getArriveeHeure(), horaire.getArriveeMinute()),
                String.format("%02d:%02d", horaire.getDepartHeure(), horaire.getDepartMinute())
            });
        }
        trainList.setModel(model);

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        trainList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        trainList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(trainList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable trainList;
    // End of variables declaration//GEN-END:variables
}
