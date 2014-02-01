package paulenka.aleh.wordbook.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.dao.mapper.UserMapper;
import paulenka.aleh.wordbook.db.JdbcDaoTemplate;
import paulenka.aleh.wordbook.db.table.UserTable;
import paulenka.aleh.wordbook.entity.Credentials;
import paulenka.aleh.wordbook.entity.Registration;
import paulenka.aleh.wordbook.entity.User;
import paulenka.aleh.wordbook.util.HashingUtil;

public class UserDao extends JdbcDaoTemplate {

    private final static String QUERY_LOGIN = "SELECT `" + UserTable.ID + "`, `" + UserTable.USERNAME + "`, `" + UserTable.PASSWORD +
            "` FROM `" + UserTable.TABLE + "` WHERE `" + UserTable.USERNAME + "` = ? AND `" + UserTable.PASSWORD + "` = ? LIMIT 1;";
    private final static String QUERY_IS_USER_EXISTS = "SELECT `" + UserTable.ID +
            "` FROM `" + UserTable.TABLE + "` WHERE `" + UserTable.USERNAME + "` = ? LIMIT 1;";
    private final static String QUERY_CREATE_USER = "INSERT INTO `" + UserTable.TABLE +
            "` (`" + UserTable.USERNAME + "`, `" + UserTable.PASSWORD + "`) VALUES (?, ?);";
    private final static String QUERY_GET_USER = "SELECT * FROM `" + UserTable.TABLE + "` WHERE `" + UserTable.ID + "` = ? LIMIT 1;";
    private final static String QUERY_LIST_USERS = "SELECT * FROM `" + UserTable.TABLE + "`;";

    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        if (userMapper == null) {
            userMapper = new UserMapper();
        }
        return userMapper;
    }

    public User login(Credentials credentials) throws SQLException {
        try {
            String username = credentials.getUsername();
            byte[] passwordHash = HashingUtil.sha2(credentials.getPassword());

            return executeQueryWithSingleResult(getUserMapper(), QUERY_LOGIN, username, passwordHash);
        } catch (NoSuchAlgorithmException ex) {
            throw new SQLException(ex);
        }
    }

    public boolean isUserExists(String username) throws SQLException {
        return executeQueryWithSingleResult(getUserMapper(), QUERY_IS_USER_EXISTS, username) != null;
    }

    public User register(Registration registration) throws SQLException {
        try {
            String username = registration.getUsername();
            byte[] passwordHash = HashingUtil.sha2(registration.getPassword());

            int id = executeInsert(QUERY_CREATE_USER, username, passwordHash);
            return executeQueryWithSingleResult(getUserMapper(), QUERY_GET_USER, id);
        } catch (NoSuchAlgorithmException ex) {
            throw new SQLException(ex);
        }

    }

    public List<User> list() throws SQLException {
        return executeQuery(getUserMapper(), QUERY_LIST_USERS);
    }
}