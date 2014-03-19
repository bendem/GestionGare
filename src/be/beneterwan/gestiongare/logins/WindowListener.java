package be.beneterwan.gestiongare.logins;

import java.awt.event.WindowEvent;

public class WindowListener implements java.awt.event.WindowListener {
    
    private final LoginFrame frame;

    public WindowListener(LoginFrame frame) {
        this.frame = frame;
        
    }

    @Override
    public void windowOpened(WindowEvent we) {}

    @Override
    public void windowClosing(WindowEvent we) {
        System.out.println("Closing...");
        frame.dispose();
    }

    @Override
    public void windowClosed(WindowEvent we) {}

    @Override
    public void windowIconified(WindowEvent we) {}

    @Override
    public void windowDeiconified(WindowEvent we) {}

    @Override
    public void windowActivated(WindowEvent we) {}

    @Override
    public void windowDeactivated(WindowEvent we) {}
    
}
