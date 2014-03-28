package be.beneterwan.gestiongare.applicgare;

import be.beneterwan.gestiongare.logins.LoginEvent;
import be.beneterwan.gestiongare.logins.LoginListener;

/**
 * @author bendem
 */
public class ApplicGareFrameEventHandler implements LoginListener {

    protected final ApplicGareFrame frame;

    public ApplicGareFrameEventHandler(ApplicGareFrame frame) {
        this.frame = frame;
    }

    @Override
    public void onLogin(LoginEvent event) {
        frame.setLoggedIn(true);
    }

}
