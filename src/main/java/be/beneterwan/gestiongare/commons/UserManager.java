package be.beneterwan.gestiongare.commons;

import be.beneterwan.gestiongare.authenticate.User;
import be.beneterwan.gestiongare.commons.logger.CustomLogger;
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
public class UserManager {

    private final static Logger LOGGER = new CustomLogger(UserManager.class.getSimpleName());
    private final static String FILE_NAME = "users.dat";
    protected static UserManager instance;

    protected final Set<User> users;

    protected UserManager() {
        users = new HashSet<>();
        load();
    }

    public Set<User> getUsers() {
        return users;
    }

    public User getUserByLogin(String login) {
        for(User user : users) {
            if(user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public boolean add(User user) {
        if(getUserByLogin(user.getLogin()) != null) {
            return false;
        }
        users.add(user);
        return true;
    }

    public void save() {
        LOGGER.info("Saving users...");
        File userFile = new File("." + File.separator + FILE_NAME);
        try {
            userFile.createNewFile();
            ObjectSaver saver = new ObjectSaver(FILE_NAME);
            saver.save(users);
        } catch(IOException ex) {
            LOGGER.log(Level.SEVERE, "Accès à " + userFile + " impossible!", ex);
        }
    }

    public void load() {
        LOGGER.info("Loading users...");
        File userFile = new File("." + File.separator + FILE_NAME);
        if(userFile.exists()) {
            ObjectLoader loader = new ObjectLoader(FILE_NAME);
            try {
                @SuppressWarnings("unchecked")
                Set<User> usersFromFile = (Set<User>) loader.load();
                users.addAll(usersFromFile);
            } catch(IOException | ClassNotFoundException ex) {
                LOGGER.log(Level.SEVERE, "Impossible de charger le fichier!", ex);
            }
        } else {
            users.add(new User("bendem", "yolo", true));
            save();
        }
    }

    public static UserManager getInstance(){
        if (instance == null){
            instance = new UserManager();
        }

        return instance;
    }

}
