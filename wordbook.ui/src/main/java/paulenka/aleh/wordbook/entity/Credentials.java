package paulenka.aleh.wordbook.entity;

import paulenka.aleh.wordbook.util.Entity;

public class Credentials extends Entity {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public Credentials() {
        this(null, null);
    }

    public Credentials(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}