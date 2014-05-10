package be.beneterwan.gestiongare.authenticate;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author bendem & Curlybear
 */
public class User implements Serializable {

    private String login;
    private String password;
    private boolean admin;

    public User(String login, String password) {
        this(login, password, false);
    }

    public User(String login, String password, boolean admin) {
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.login);
        hash = 89 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if(!Objects.equals(this.login, other.login)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

}
