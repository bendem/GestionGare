package be.beneterwan.gestiongare.applicgare.dialogs;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.applicgare.incidents.IncidentEvent;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 * @author bendem & Curlybear
 */
public class IncidentListDialog extends javax.swing.JDialog {

    public IncidentListDialog(ApplicGareFrame frame) {
        super(frame, "Liste des incident du jour", true);
        initComponents();

        // Loading train list
        LinkedList<IncidentEvent> list = frame.getApplicGare().getListEvents();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Message de l'incident");


        for(IncidentEvent incident : list) {
            if(incident == null) {
                continue;
            }

            model.addRow(new Object[] {
                incident.getIncidentDate(),
                incident.getIncidentMessage()
            });
        }
        incidentList.setModel(model);

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        incidentList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        incidentList.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(incidentList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable incidentList;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
