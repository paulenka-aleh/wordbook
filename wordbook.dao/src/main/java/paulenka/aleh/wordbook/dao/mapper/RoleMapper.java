package paulenka.aleh.wordbook.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import paulenka.aleh.wordbook.dao.table.RoleTable;
import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.db.JdbcEntityMapper;

public class RoleMapper implements JdbcEntityMapper<Role> {

    @Override
    public Role map(ResultSet result) throws SQLException {
        return Role.getRoleById(result.getInt(RoleTable.ROLE_ID));
    }
}