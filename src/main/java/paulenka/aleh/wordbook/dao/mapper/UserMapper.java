package paulenka.aleh.wordbook.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import paulenka.aleh.wordbook.db.JdbcEntityMapper;
import paulenka.aleh.wordbook.db.table.UserTable;
import paulenka.aleh.wordbook.entity.User;

public class UserMapper implements JdbcEntityMapper<User> {

    @Override
    public User map(ResultSet result) throws SQLException {
        return new User(result.getInt(UserTable.ID), result.getString(UserTable.USERNAME), result.getBytes(UserTable.PASSWORD));
    }
}