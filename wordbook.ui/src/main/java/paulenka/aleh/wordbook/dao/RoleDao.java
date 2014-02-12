package paulenka.aleh.wordbook.dao;

import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.constant.AuthorizationRole;
import paulenka.aleh.wordbook.dao.mapper.AuthorizationRoleMapper;
import paulenka.aleh.wordbook.db.JdbcDaoTemplate;
import paulenka.aleh.wordbook.db.table.RoleTable;

public class RoleDao extends JdbcDaoTemplate {

    private final static String QUERY_GET_USERS_BY_ROLE = "SELECT `" + RoleTable.ROLE_ID +
            "` FROM `" + RoleTable.TABLE + "` WHERE `" + RoleTable.USER_ID + "` = ?;";

    private AuthorizationRoleMapper authorizationRoleMapper;

    public AuthorizationRoleMapper getAuthorizationRoleMapperMapper() {
        if (authorizationRoleMapper == null) {
            authorizationRoleMapper = new AuthorizationRoleMapper();
        }
        return authorizationRoleMapper;
    }

    public List<AuthorizationRole> getUserRoles(int userId) throws SQLException {
        return executeQuery(getAuthorizationRoleMapperMapper(), QUERY_GET_USERS_BY_ROLE, userId);
    }
}