package paulenka.aleh.wordbook.dao.impl;

import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Set;

import paulenka.aleh.wordbook.dao.RoleDao;
import paulenka.aleh.wordbook.dao.mapper.RoleMapper;
import paulenka.aleh.wordbook.dao.table.RoleTable;
import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.db.JdbcDaoTemplate;
import paulenka.aleh.wordbook.db.JdbcTransaction;

public class RoleDaoImpl extends JdbcDaoTemplate implements RoleDao {

    private static final String QUERY_GET_USERS_BY_ROLE = "SELECT `" + RoleTable.ROLE_ID + "` FROM `" + RoleTable.TABLE + "` WHERE `" + RoleTable.USER_ID + "` = ?;";
    private static final String QUERY_DELETE_ROLES_BY_USER = "DELETE FROM `" + RoleTable.TABLE + "` WHERE `" + RoleTable.USER_ID + "` = ?;";
    private static final String QUERY_ASSIGN_ROLE_TO_USER = "INSERT INTO `" + RoleTable.TABLE + "` (`" + RoleTable.USER_ID + "`, `" + RoleTable.ROLE_ID + "`) VALUES (?, ?);";

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

    @Override
    public void assign(final int userId, final Set<Role> roles) throws SQLException {
        new JdbcTransaction<Void>() {

            @Override
            protected Void transaction() throws SQLException {
                executeUpdate(QUERY_DELETE_ROLES_BY_USER, userId);
                for (Role role : roles) {
                    executeInsert(QUERY_ASSIGN_ROLE_TO_USER, userId, role.getRoleId());
                }
                return null;
            }
        }.execute();
    }
}