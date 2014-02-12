package paulenka.aleh.wordbook.dao;

import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.data.Role;

public interface RoleDao {

    public List<Role> getUserRoles(int userId) throws SQLException;
}