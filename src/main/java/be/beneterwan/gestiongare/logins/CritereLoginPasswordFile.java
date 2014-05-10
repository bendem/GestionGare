package be.beneterwan.gestiongare.logins;

import be.beneterwan.gestiongare.authenticate.CritereLoginPassword;
import be.beneterwan.gestiongare.authenticate.User;
import be.beneterwan.gestiongare.commons.UserManager;

/**
 * @author bendem & Curlybear
 */
public class CritereLoginPasswordFile extends CritereLoginPassword {

    public CritereLoginPasswordFile(User user) {
        super(user);
    }

    @Override
    public String findPassword(String login) {
        User user = getUserByName(login);
        if(user == null) {
            return null;
        }
        return user.getPassword();
    }

    @Override
    public User getUserByName(String login) {
        for(User user : UserManager.getInstance().getUsers()) {
            if(user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

}
