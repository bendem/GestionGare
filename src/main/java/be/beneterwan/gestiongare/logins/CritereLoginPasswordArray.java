package be.beneterwan.gestiongare.logins;

import be.beneterwan.gestiongare.authenticate.CritereLoginPassword;
import be.beneterwan.gestiongare.authenticate.User;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bendem et Curlybear
 */
public class CritereLoginPasswordArray extends CritereLoginPassword {

    private final static Set<User> users;

    static {
        users = new HashSet<>();

        users.add(new User("bendem", "yolo"));
        users.add(new User("Bear", "swag"));
        users.add(new User("arcange", "22"));
    }

    public CritereLoginPasswordArray(User user) {
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
