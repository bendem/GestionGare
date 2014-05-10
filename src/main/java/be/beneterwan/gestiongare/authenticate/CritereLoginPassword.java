package be.beneterwan.gestiongare.authenticate;

/**
 * @author bendem & Curlybear
 */
abstract public class CritereLoginPassword implements Critere {

    abstract public String findPassword(String login);

    private final User user;

    public CritereLoginPassword(User user) {
        this.user = user;
    }

    @Override
    public boolean isOk() {
        String password = findPassword(user.getLogin());
        return password == null ? false : user.getPassword().equals(password);
    }

    abstract public User getUserByName(String login);

}
