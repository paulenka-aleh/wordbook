package paulenka.aleh.wordbook.ui.login;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import paulenka.aleh.wordbook.dao.RoleDao;
import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.dao.impl.RoleDaoImpl;
import paulenka.aleh.wordbook.dao.impl.UserDaoImpl;
import paulenka.aleh.wordbook.data.Credentials;
import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.ui.constant.SessionAttribute;

public class LoginManager {

    private UserDao userDao;
    private RoleDao roleDao;

    protected UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    protected RoleDao getRoleDao() {
        if (roleDao == null) {
            roleDao = new RoleDaoImpl();
        }
        return roleDao;
    }

    protected Map<String, Object> getSession() {
        return ServletActionContext.getActionContext(ServletActionContext.getRequest()).getSession();
    }

    public boolean login(Credentials credentials) throws SQLException {
        User user = getUserDao().login(credentials);
        if (user != null) {
            login(user);
            return true;
        } else {
            return false;
        }
    }

    public void login(User user) {
        getSession().put(SessionAttribute.USER, user);
    }

    public boolean isLoggedIn() {
        return getSession().containsKey(SessionAttribute.USER);
    }

    public void logout() {
        getSession().remove(SessionAttribute.USER);
    }

    public User getUser() {
        return (User) getSession().get(SessionAttribute.USER);
    }

    @SuppressWarnings("unchecked")
    public Set<Role> getRoles() throws SQLException {
        if (!isLoggedIn()) {
            return null;
        }
        if (!getSession().containsKey(SessionAttribute.ROLES)) {
            getSession().put(SessionAttribute.ROLES, getRoleDao().getUserRoles(getUser().getId()));
        }
        return (Set<Role>) getSession().get(SessionAttribute.ROLES);
    }
}