package paulenka.aleh.wordbook.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import paulenka.aleh.wordbook.constant.AuthorizationRole;
import paulenka.aleh.wordbook.db.JdbcEntityMapper;
import paulenka.aleh.wordbook.db.table.RoleTable;

public class AuthorizationRoleMapper implements JdbcEntityMapper<AuthorizationRole> {

    @Override
    public AuthorizationRole map(ResultSet result) throws SQLException {
        return AuthorizationRole.getRoleById(result.getInt(RoleTable.ROLE_ID));
    }
}