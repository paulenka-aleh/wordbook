package paulenka.aleh.wordbook.dao.impl;

import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.dao.RoleDao;
import paulenka.aleh.wordbook.dao.mapper.RoleMapper;
import paulenka.aleh.wordbook.dao.table.RoleTable;
import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.db.JdbcDaoTemplate;

public class RoleDaoImpl extends JdbcDaoTemplate implements RoleDao {

    private final static String QUERY_GET_USERS_BY_ROLE = "SELECT `" + RoleTable.ROLE_ID + "` FROM `" + RoleTable.TABLE + "` WHERE `" + RoleTable.USER_ID + "` = ?;";

    private RoleMapper roleMapper;

    public RoleMapper getRoleMapperMapper() {
        if (roleMapper == null) {
            roleMapper = new RoleMapper();
        }
        return roleMapper;
    }

    public List<Role> getUserRoles(int userId) throws SQLException {
        return executeQuery(getRoleMapperMapper(), QUERY_GET_USERS_BY_ROLE, userId);
    }
}