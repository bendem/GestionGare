package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.authenticate.User;
import be.beneterwan.gestiongare.logger.CustomLogger;
import be.beneterwan.gestiongare.logins.LoginEvent;
import be.beneterwan.gestiongare.logins.LoginListener;
import java.util.logging.Logger;

/**
 * @author bendem et Curlybear
 */
public class ApplicGareFrameEventHandler implements LoginListener {

    private static final Logger LOGGER = new CustomLogger(ApplicGareFrameEventHandler.class.getSimpleName());

    protected final ApplicGareFrame frame;

    public ApplicGareFrameEventHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    @Override
    public void onLogin(LoginEvent event) {
        User user = event.getUser();
        LOGGER.info(user.getLogin() + " just logged in as " + (user.isAdmin() ? "admin" : "user") + "...");
        frame.setLoggedIn(user);
        frame.getFenLogin().dispose();
    }

}
