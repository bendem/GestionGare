package be.beneterwan.gestiongare.applicgare.handlers;

import be.beneterwan.gestiongare.applicgare.ApplicGareFrame;
import be.beneterwan.gestiongare.commons.eventmanagement.EventHandler;
import be.beneterwan.gestiongare.authenticate.User;
import be.beneterwan.gestiongare.logins.LoginEvent;
import java.util.EventObject;

/**
 * @author bendem et Curlybear
 */
public class LoginHandler implements EventHandler {

    private final ApplicGareFrame frame;

    public LoginHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    @Override
    public void execute(EventObject event) {
        User user = ((LoginEvent) event).getUser();
        frame.setLoggedIn(user);
        frame.getFenLogin().dispose();
    }
}
