package be.beneterwan.gestiongare.logins;

import be.beneterwan.gestiongare.authenticate.Critere;
import be.beneterwan.gestiongare.authenticate.User;
import be.beneterwan.gestiongare.logger.CustomLogger;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

/**
 * @author bendem
 */
public class LoginFrame extends Frame implements ActionListener {

    private static final Logger LOGGER = new CustomLogger(LoginFrame.class.getSimpleName());

    private final TextField login;
    private final TextField pwd;
    private final Button ok;
    private final Button abort;

    public LoginFrame() {
        super("YOLO");
        setLayout(new GridLayout(3, 2));
        setVisible(true);
        setLocationRelativeTo(null);

        // Add stuff
        login = new TextField();
        pwd = new TextField();
        pwd.setEchoChar('*');
        ok = new Button("OK");
        abort = new Button("abort");

        add(new Label("Votre Login"));
        add(login);
        add(new Label("Votre mot de passe"));
        add(pwd);
        add(ok);
        add(abort);

        ok.addActionListener(this);
        abort.addActionListener(this);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                LOGGER.info("Closing...");
                we.getWindow().dispose();
            }
        });
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("OK")) {
            Critere critereLoginPwdArray = new CritereLoginPasswordArray(new User(login.getText(), pwd.getText()));
            if(critereLoginPwdArray.isOk()) {
                LOGGER.info("Oui");
            } else {
                LOGGER.info("Non");
            }
        } else {
            dispose();
        }
    }

}
