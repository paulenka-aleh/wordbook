package paulenka.aleh.wordbook.dao.impl;

import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Set;

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

    @Override
    public Set<Role> getUserRoles(int userId) throws SQLException {
        Set<Role> result = EnumSet.noneOf(Role.class);
        result.addAll(executeQuery(getRoleMapperMapper(), QUERY_GET_USERS_BY_ROLE, userId));
        return result;
    }
}