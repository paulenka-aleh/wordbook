package paulenka.aleh.wordbook.entity;

import paulenka.aleh.wordbook.util.Entity;

public class User extends Entity {

    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private byte[] password;

    public User() {
        this(0, null, null);
    }

    public User(String username, byte[] password) {
        this(0, username, password);
    }

    public User(int id, String username, byte[] password) {
        setId(id);
        setUsername(username);
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }
}
