package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.applicgare.events.AbstractEventHandler;
import be.beneterwan.gestiongare.applicgare.events.EventHandler;
import be.beneterwan.gestiongare.logger.CustomLogger;
import be.beneterwan.gestiongare.logins.LoginEvent;
import be.beneterwan.gestiongare.logins.LoginFrame;
import be.beneterwan.gestiongare.logins.LoginListener;
import java.util.logging.Logger;
import javax.swing.JMenuItem;

/**
 * @author bendem et Curlybear
 */
public class ApplicGareFrameEventHandler extends AbstractEventHandler implements LoginListener {

    private static final Logger LOGGER = new CustomLogger(ApplicGareFrameEventHandler.class.getSimpleName());

    protected final ApplicGareFrame frame;

    public ApplicGareFrameEventHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    public void addListener(LoginFrame loginFrame, EventHandler handler) {
        loginFrame.addLoginListener(this);
        registerHandler(loginFrame, handler);
    }

    public void addListener(JMenuItem item, EventHandler handler) {
        item.addActionListener(this);
        registerHandler(item, handler);
    }

    @Override
    public void onLogin(LoginEvent event) {
        LOGGER.info("Login performed : " + event.getUser().getLogin() + (event.getUser().isAdmin() ? " (a)" : ""));
        dispatchEvent(event);
    }

}
