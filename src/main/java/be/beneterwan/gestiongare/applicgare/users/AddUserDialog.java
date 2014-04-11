package be.beneterwan.gestiongare.applicgare.users;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.authenticate.User;
import be.beneterwan.gestiongare.commons.UserManager;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.eventmanagement.EventManager;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.util.EventObject;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public class AddUserDialog extends javax.swing.JDialog {

    private static final Logger LOGGER = new CustomLogger(AddUserDialog.class.getSimpleName());
    private UserManager userManager = UserManager.getInstance();
    private final EventManager eventManager;

    public AddUserDialog(ApplicGareFrame parent) {
        super(parent, "Nouvel utilisateur", true);
        initComponents();
        eventManager = new EventManager();

        eventManager.addListener(buttonAnnuler, new EventHandler() {
            @Override
            public void execute(EventObject event) {
                dispose();
            }
        });
        eventManager.addListener(buttonConfirmer, new EventHandler() {
            @Override
            public void execute(EventObject event) {
                if(!setErrorMessage()) {
                    LOGGER.info("Adding new user...");
                    User newUser = new User(textFieldNomUser.getText(), new String(passwordFieldMdP1.getPassword()), checkBoxAdmin.isSelected());
                    if(userManager.add(newUser)) {
                        userManager.save();
                        dispose();
                    }
                    errorMessage.setText("O_o");
                }
            }
        });

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private boolean setErrorMessage() {
        if(textFieldNomUser.getText().isEmpty()) {
            errorMessage.setText("Le nom d'utilisateur est requis");
            return true;
        }
        String password1 = new String(passwordFieldMdP1.getPassword());
        String password2 = new String(passwordFieldMdP2.getPassword());
        if(password1.isEmpty() || password2.isEmpty()) {
            errorMessage.setText("Le mot de passe est requis");
            return true;
        }
        if(!password1.equals(password2)) {
            errorMessage.setText("Les mots de passe doivent correspondre");
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldNomUser = new javax.swing.JTextField();
        checkBoxAdmin = new javax.swing.JCheckBox();
        labelNomUser = new javax.swing.JLabel();
        labelMdP1 = new javax.swing.JLabel();
        labelAdmin = new javax.swing.JLabel();
        buttonConfirmer = new javax.swing.JButton();
        buttonAnnuler = new javax.swing.JButton();
        labelMdP2 = new javax.swing.JLabel();
        passwordFieldMdP1 = new javax.swing.JPasswordField();
        passwordFieldMdP2 = new javax.swing.JPasswordField();
        errorMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelNomUser.setText("Nom de l'utilisateur :");

        labelMdP1.setText("Mot de passe de l'utilisateur :");

        labelAdmin.setText("Administrateur? :");

        buttonConfirmer.setText("Confirmer");

        buttonAnnuler.setText("Annuler");

        labelMdP2.setText("Confirmer le mot de passe :");

        errorMessage.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNomUser)
                            .addComponent(labelMdP1)
                            .addComponent(labelAdmin)
                            .addComponent(labelMdP2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkBoxAdmin)
                            .addComponent(textFieldNomUser)
                            .addComponent(passwordFieldMdP2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(passwordFieldMdP1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 192, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonConfirmer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldNomUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNomUser))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMdP1)
                    .addComponent(passwordFieldMdP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMdP2)
                    .addComponent(passwordFieldMdP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxAdmin)
                    .addComponent(labelAdmin))
                .addGap(5, 5, 5)
                .addComponent(errorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConfirmer)
                    .addComponent(buttonAnnuler))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAnnuler;
    private javax.swing.JButton buttonConfirmer;
    private javax.swing.JCheckBox checkBoxAdmin;
    private javax.swing.JLabel errorMessage;
    private javax.swing.JLabel labelAdmin;
    private javax.swing.JLabel labelMdP1;
    private javax.swing.JLabel labelMdP2;
    private javax.swing.JLabel labelNomUser;
    private javax.swing.JPasswordField passwordFieldMdP1;
    private javax.swing.JPasswordField passwordFieldMdP2;
    private javax.swing.JTextField textFieldNomUser;
    // End of variables declaration//GEN-END:variables
}
