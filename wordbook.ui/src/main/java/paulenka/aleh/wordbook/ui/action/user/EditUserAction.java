package paulenka.aleh.wordbook.ui.action.user;

import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Set;

import paulenka.aleh.wordbook.dao.RoleDao;
import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.dao.impl.RoleDaoImpl;
import paulenka.aleh.wordbook.dao.impl.UserDaoImpl;
import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.ui.action.common.ProcessFormAction;
import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;

@Authorization
public class EditUserAction extends ProcessFormAction {

    private static final long serialVersionUID = 1L;

    private UserDao userDao;
    private RoleDao roleDao;

    private User user;
    private Set<Role> roles;
    private Set<Role> userRoles;

    private boolean changePassword;
    private String password;
    private String confirmedPassword;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> roles) {
        this.userRoles = roles;
    }

    public boolean isChangePassword() {
        return changePassword;
    }

    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    @Override
    public String view() {
        try {
            setUser(getUserDao().get(getUser().getId()));
            setRoles(EnumSet.allOf(Role.class));
            setUserRoles(getRoleDao().getUserRoles(getUser().getId()));
            return INPUT;
        } catch (SQLException ex) {
            // TODO: Redirect to 500 page
            ex.printStackTrace();
            return ERROR;
        }
    }

    @Override
    public String process() {

        return SUCCESS;
    }

    @Override
    public void validate() {

    }
}