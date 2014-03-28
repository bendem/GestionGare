package be.beneterwan.gestiongare.logins;

import be.beneterwan.gestiongare.authenticate.User;

/**
 * @author bendem et Curlybear
 */
public class LoginEvent {

    private final User user;

    public LoginEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
