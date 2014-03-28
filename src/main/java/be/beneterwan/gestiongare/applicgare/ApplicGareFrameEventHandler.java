package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.logger.CustomLogger;
import be.beneterwan.gestiongare.logins.LoginEvent;
import be.beneterwan.gestiongare.logins.LoginListener;
import java.util.logging.Logger;

/**
 * @author bendem
 */
public class ApplicGareFrameEventHandler implements LoginListener {

    private static Logger LOGGER = new CustomLogger(ApplicGareFrameEventHandler.class.getSimpleName());

    protected final ApplicGareFrame frame;

    public ApplicGareFrameEventHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    @Override
    public void onLogin(LoginEvent event) {
        LOGGER.info(event.getUser().getLogin() + " just logged in...");
        frame.setLoggedIn(true);
    }

}
