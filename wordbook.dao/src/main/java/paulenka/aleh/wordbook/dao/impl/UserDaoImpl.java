package paulenka.aleh.wordbook.dao.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.dao.mapper.UserMapper;
import paulenka.aleh.wordbook.dao.table.RoleTable;
import paulenka.aleh.wordbook.dao.table.UserTable;
import paulenka.aleh.wordbook.dao.util.HashingUtil;
import paulenka.aleh.wordbook.data.Credentials;
import paulenka.aleh.wordbook.data.Registration;
import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.db.JdbcDaoTemplate;
import paulenka.aleh.wordbook.db.JdbcTransaction;

public class UserDaoImpl extends JdbcDaoTemplate implements UserDao {

    private static final String QUERY_LOGIN = "SELECT * FROM `" + UserTable.TABLE + "` WHERE `" + UserTable.USERNAME + "` = ? AND `" + UserTable.PASSWORD + "` = ? LIMIT 1;";
    private static final String QUERY_IS_USER_EXISTS = "SELECT * FROM `" + UserTable.TABLE + "` WHERE `" + UserTable.USERNAME + "` = ? LIMIT 1;";
    private static final String QUERY_CREATE_USER = "INSERT INTO `" + UserTable.TABLE + "` (`" + UserTable.USERNAME + "`, `" + UserTable.PASSWORD + "`) VALUES (?, ?);";
    private static final String QUERY_UPDATE_PASSWORD = "UPDATE `" + UserTable.TABLE + "` SET `" + UserTable.PASSWORD + "` = ? WHERE `" + UserTable.ID + "` = ? LIMIT 1;";
    private static final String QUERY_DELETE_USER = "DELETE FROM `" + UserTable.TABLE + "` WHERE `" + UserTable.ID + "` = ? LIMIT 1;";
    private static final String QUERY_DELETE_USER_FROM_ROLES = "DELETE FROM `" + RoleTable.TABLE + "` WHERE `" + RoleTable.USER_ID + "` = ?;";
    private static final String QUERY_GET_USER = "SELECT * FROM `" + UserTable.TABLE + "` WHERE `" + UserTable.ID + "` = ? LIMIT 1;";
    private static final String QUERY_LIST_USERS = "SELECT * FROM `" + UserTable.TABLE + "`;";

    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        if (userMapper == null) {
            userMapper = new UserMapper();
        }
        return userMapper;
    }

    @Override
    public User login(Credentials credentials) throws SQLException {
        try {
            String username = credentials.getUsername();
            byte[] passwordHash = HashingUtil.sha2(credentials.getPassword());

            return executeQueryWithSingleResult(getUserMapper(), QUERY_LOGIN, username, passwordHash);
        } catch (NoSuchAlgorithmException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public boolean isUsernameExists(String username) throws SQLException {
        return executeQueryWithSingleResult(getUserMapper(), QUERY_IS_USER_EXISTS, username) != null;
    }

    @Override
    public User register(Registration registration) throws SQLException {
        try {
            final String username = registration.getUsername();
            final byte[] passwordHash = HashingUtil.sha2(registration.getPassword());

            return new JdbcTransaction<User>() {

                @Override
                protected User transaction() throws SQLException {
                    int id = executeInsert(QUERY_CREATE_USER, username, passwordHash);
                    return executeQueryWithSingleResult(getUserMapper(), QUERY_GET_USER, id);
                }
            }.execute();
        } catch (NoSuchAlgorithmException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public User get(int id) throws SQLException {
        return executeQueryWithSingleResult(getUserMapper(), QUERY_GET_USER, id);
    }

    @Override
    public List<User> list() throws SQLException {
        return executeQuery(getUserMapper(), QUERY_LIST_USERS);
    }

    @Override
    public void delete(final int id) throws SQLException {
        new JdbcTransaction<Void>() {

            @Override
            protected Void transaction() throws SQLException {
                executeUpdate(QUERY_DELETE_USER, id);
                executeUpdate(QUERY_DELETE_USER_FROM_ROLES, id);

                return null;
            }
        }.execute();
    }

    @Override
    public void updatePassword(int id, String password) throws SQLException {
        try {
            executeUpdate(QUERY_UPDATE_PASSWORD, HashingUtil.sha2(password), id);
        } catch (NoSuchAlgorithmException ex) {
            throw new SQLException(ex);
        }
    }
}