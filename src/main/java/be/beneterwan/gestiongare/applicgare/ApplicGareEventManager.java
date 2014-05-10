package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.commons.eventmanagement.NetworkEventManager;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import be.beneterwan.gestiongare.logins.LoginEvent;
import be.beneterwan.gestiongare.logins.LoginFrame;
import be.beneterwan.gestiongare.logins.LoginListener;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public class ApplicGareEventManager extends NetworkEventManager implements LoginListener {

    private static final Logger LOGGER = new CustomLogger(ApplicGareEventManager.class.getSimpleName());

    public void addListener(LoginFrame loginFrame, EventHandler handler) {
        loginFrame.addLoginListener(this);
        registerHandler(loginFrame, handler);
    }

    @Override
    public void onLogin(LoginEvent event) {
        LOGGER.fine("Login performed : " + event.getUser().getLogin() + (event.getUser().isAdmin() ? " (a)" : ""));
        dispatchEvent(event);
    }

}
