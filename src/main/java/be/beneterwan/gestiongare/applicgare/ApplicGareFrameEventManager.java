package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.applicgare.events.AbstractEventManager;
import be.beneterwan.gestiongare.applicgare.events.EventHandler;
import be.beneterwan.gestiongare.logger.CustomLogger;
import be.beneterwan.gestiongare.logins.LoginEvent;
import be.beneterwan.gestiongare.logins.LoginFrame;
import be.beneterwan.gestiongare.logins.LoginListener;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public class ApplicGareFrameEventManager extends AbstractEventManager implements LoginListener {

    private static final Logger LOGGER = new CustomLogger(ApplicGareFrameEventManager.class.getSimpleName());

    protected final ApplicGareFrame frame;

    public ApplicGareFrameEventManager(ApplicGareFrame frame) {
        this.frame = frame;
    }

    public void addListener(LoginFrame loginFrame, EventHandler handler) {
        loginFrame.addLoginListener(this);
        registerHandler(loginFrame, handler);
    }

    @Override
    public void onLogin(LoginEvent event) {
        LOGGER.info("Login performed : " + event.getUser().getLogin() + (event.getUser().isAdmin() ? " (a)" : ""));
        dispatchEvent(event);
    }

}
