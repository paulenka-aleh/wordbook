package paulenka.aleh.wordbook.dao;

import java.sql.SQLException;
import java.util.Set;

import paulenka.aleh.wordbook.data.Role;

public interface RoleDao {

    public Set<Role> getUserRoles(int userId) throws SQLException;
}