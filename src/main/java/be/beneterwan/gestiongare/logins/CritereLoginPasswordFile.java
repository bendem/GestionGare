package be.beneterwan.gestiongare.logins;

import be.beneterwan.gestiongare.applicgare.ApplicGare;
import static be.beneterwan.gestiongare.commons.UserManager.FILE_NAME;
import be.beneterwan.gestiongare.authenticate.CritereLoginPassword;
import be.beneterwan.gestiongare.authenticate.User;
import be.beneterwan.gestiongare.commons.UserManager;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import serialize.ObjectLoader;
import serialize.ObjectSaver;

/**
 * @author bendem et Curlybear
 */
public class CritereLoginPasswordFile extends CritereLoginPassword {

    private static final Logger LOGGER = new CustomLogger(CritereLoginPasswordFile.class.getSimpleName());
    private static final UserManager userManager = UserManager.getInstance();
    private static final Set<User> users = userManager.getUsers();

    static {
        userManager.load();
    }

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
        for(User user : users) {
            if(user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

}
