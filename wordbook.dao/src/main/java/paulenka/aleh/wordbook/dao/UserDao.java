package paulenka.aleh.wordbook.dao;

import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.data.Credentials;
import paulenka.aleh.wordbook.data.Registration;
import paulenka.aleh.wordbook.data.User;

public interface UserDao {

    public User login(Credentials credentials) throws SQLException;

    public boolean isUsernameExists(String username) throws SQLException;

    public User register(Registration registration) throws SQLException;

    public User get(int id) throws SQLException;

    public List<User> list() throws SQLException;

    public void delete(int id) throws SQLException;
}