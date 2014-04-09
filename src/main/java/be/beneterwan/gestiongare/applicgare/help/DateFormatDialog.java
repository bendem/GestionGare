package be.beneterwan.gestiongare.applicgare.help;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.applicgare.DateFormat;
import be.beneterwan.gestiongare.applicgare.events.EventHandler;
import be.beneterwan.gestiongare.applicgare.events.EventManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.EventObject;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * @author bendem et Curlybear
 */
public class DateFormatDialog extends javax.swing.JDialog {

    private final EventManager eventManager;
    private DateFormat dateFormat = new DateFormat();

    public DateFormatDialog(ApplicGareFrame parent) {
        super(parent, "Date format", true);
        initComponents();

        // Add custom combobox values
        comboBoxPays.setModel(new DefaultComboBoxModel<>(DateFormat.Country.values()));

        // TODO Use parent.getDateFormat to set default values
        parent.getDateFormat();
        comboBoxPays.setSelectedItem(dateFormat.getCountry());
        comboBoxFormatDate.setSelectedItem(dateFormat.getDateFormat());
        comboBoxFormatHeure.setSelectedItem(dateFormat.getTimeFormat());
        refreshSampleContent();
        // Setting up events
        eventManager = new EventManager();
        eventManager.addListener(bouttonAnnuler, new EventHandler() {
            @Override
            public void execute(EventObject event) {
                dispose();
            }
        });
        eventManager.addListener(bouttonOK, new OkHandler(this));

        JComboBoxValueChangeHandler cBoxHandler = new JComboBoxValueChangeHandler(this);
        eventManager.addListener(comboBoxFormatHeure, cBoxHandler);
        eventManager.addListener(comboBoxFormatDate, cBoxHandler);
        eventManager.addListener(comboBoxPays, cBoxHandler);

        // Repacking window
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public final void refreshSampleContent() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat.getDateFormat() + ", " + dateFormat.getTimeFormat());
        sampleContent.setText(dateFormatter.format(calendar.getTime()));
    }

    public JComboBox<String> getComboBoxFormatDate() {
        return comboBoxFormatDate;
    }

    public JComboBox<String> getComboBoxFormatHeure() {
        return comboBoxFormatHeure;
    }

    public JComboBox<DateFormat.Country> getComboBoxPays() {
        return comboBoxPays;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
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

        comboBoxPays = new javax.swing.JComboBox<>();
        comboBoxFormatDate = new javax.swing.JComboBox<>();
        comboBoxFormatHeure = new javax.swing.JComboBox<>();
        labelPays = new javax.swing.JLabel();
        labelFormatDate = new javax.swing.JLabel();
        labelFormatHeure = new javax.swing.JLabel();
        bouttonOK = new javax.swing.JButton();
        bouttonAnnuler = new javax.swing.JButton();
        sampleText = new javax.swing.JLabel();
        sampleContent = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        comboBoxFormatDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd-MM-YY", "dd-MM-YYYY", "dd/MM/YY", "dd/MM/YYYY", "MM-dd-YY", "MM-dd-YYYY", "MM/dd/YY", "MM/dd/YYYY", "YY-dd-MM", "YY-MM-dd", "YY/dd/MM", "YY/MM/dd", "YYYY-dd-MM", "YYYY-MM-dd", "YYYY/dd/MM", "YYYY/MM/dd" }));

        comboBoxFormatHeure.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "h a. m", "H:m", "H:m:s", "HH:mm", "HH:mm:ss" }));

        labelPays.setText("Pays");

        labelFormatDate.setText("Format date");

        labelFormatHeure.setText("Format heure");

        bouttonOK.setText("OK");

        bouttonAnnuler.setText("Annuler");

        sampleText.setText("Exemple : ");

        sampleContent.setText("lol");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(labelFormatDate)
                            .add(labelFormatHeure)
                            .add(labelPays))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 127, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(comboBoxFormatHeure, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(comboBoxFormatDate, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(comboBoxPays, 0, 188, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(sampleText)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(sampleContent)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(bouttonOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(bouttonAnnuler)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(comboBoxPays, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(labelPays))
                .add(46, 46, 46)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(comboBoxFormatDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(labelFormatDate))
                .add(42, 42, 42)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(comboBoxFormatHeure, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(labelFormatHeure))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 35, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(bouttonAnnuler)
                    .add(bouttonOK)
                    .add(sampleText)
                    .add(sampleContent))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton bouttonAnnuler;
    protected javax.swing.JButton bouttonOK;
    protected javax.swing.JComboBox<String> comboBoxFormatDate;
    protected javax.swing.JComboBox<String> comboBoxFormatHeure;
    protected javax.swing.JComboBox<DateFormat.Country> comboBoxPays;
    protected javax.swing.JLabel labelFormatDate;
    protected javax.swing.JLabel labelFormatHeure;
    protected javax.swing.JLabel labelPays;
    protected javax.swing.JLabel sampleContent;
    protected javax.swing.JLabel sampleText;
    // End of variables declaration//GEN-END:variables
}
