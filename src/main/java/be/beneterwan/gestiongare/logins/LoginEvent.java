package be.beneterwan.gestiongare.logins;

import be.beneterwan.gestiongare.authenticate.User;
import java.util.EventObject;

/**
 * @author bendem et Curlybear
 */
public class LoginEvent extends EventObject {

    private final User user;

    public LoginEvent(User user, Object source) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
