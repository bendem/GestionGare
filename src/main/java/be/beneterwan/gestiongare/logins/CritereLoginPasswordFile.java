package be.beneterwan.gestiongare.logins;

import be.beneterwan.gestiongare.authenticate.CritereLoginPassword;
import be.beneterwan.gestiongare.authenticate.User;
import be.beneterwan.gestiongare.logger.CustomLogger;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
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
    private static final Set<User> users = new HashSet<>();
    public static final  String FILE_NAME = "users.dat";

    static {
        File userFile = new File("." + File.separator + FILE_NAME);
        if(userFile.exists()) {
            ObjectLoader loader = new ObjectLoader(FILE_NAME);
            try {
                users.addAll((Set<User>) loader.load());
            } catch(IOException | ClassNotFoundException ex) {
                LOGGER.log(Level.SEVERE, "Impossible de charger le fichier!", ex);
            }
        } else {
            users.add(new User("bendem", "yolo", true));
            try {
                userFile.createNewFile();
                ObjectSaver saver = new ObjectSaver(FILE_NAME);
                saver.save(users);
            } catch(IOException ex) {
                LOGGER.log(Level.SEVERE, "Accès à " + userFile + " impossible!", ex);
            }
        }
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
