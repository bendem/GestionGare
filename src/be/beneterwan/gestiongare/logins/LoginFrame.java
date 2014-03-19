package be.beneterwan.gestiongare.logins;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends Frame implements ActionListener {
    
    private final TextField login;
    private final TextField pwd;
    private final Button ok;
    private final Button abort;

    public LoginFrame() {
        super("YOLO");
        this.addWindowListener(new WindowListener(this));
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
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("OK")) {
            System.out.println("Login : '" + login.getText() + "', mot de passe : '" + pwd.getText() + '\'');
        } else {
            dispose();
        }
    }
    
}
