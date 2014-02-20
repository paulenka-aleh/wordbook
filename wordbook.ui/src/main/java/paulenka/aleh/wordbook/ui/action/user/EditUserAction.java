package paulenka.aleh.wordbook.ui.action.user;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import paulenka.aleh.wordbook.dao.RoleDao;
import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.dao.impl.RoleDaoImpl;
import paulenka.aleh.wordbook.dao.impl.UserDaoImpl;
import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.ui.action.common.ProcessFormAction;
import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;

@Authorization(roles = { Role.ADMINISTRATOR })
public class EditUserAction extends ProcessFormAction {

    private static final long serialVersionUID = 1L;

    private UserDao userDao;
    private RoleDao roleDao;

    private User user;
    private Map<Role, Boolean> roles;

    private boolean changePassword;
    private String password;
    private String confirmedPassword;

    private boolean success;

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

    public Map<Role, Boolean> getRoles() {
        return roles;
    }

    public void setRoles(Map<Role, Boolean> roles) {
        this.roles = roles;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String execute() throws Exception {
        try {
            setSuccess(false);
            setUser(getUserDao().get(getUser().getId()));
            if (getRoles() == null) {
                setRoles(new TreeMap<Role, Boolean>());
                Set<Role> userRoles = getRoleDao().getUserRoles(getUser().getId());
                for (Role role : Role.values()) {
                    getRoles().put(role, userRoles.contains(role));
                }
            }
            return super.execute();
        } catch (SQLException ex) {
            return ERROR;
        }
    }

    @Override
    public String view() {
        return INPUT;
    }

    @Override
    public String process() {
        try {
            processRoles();
            processPassword();
            setSuccess(true);
            return SUCCESS;
        } catch (SQLException ex) {
            return ERROR;
        }
    }

    protected void processPassword() throws SQLException {
        if (isChangePassword()) {
            getUserDao().updatePassword(getUser().getId(), getPassword());
        }
    }

    protected void processRoles() throws SQLException {
        Set<Role> roles = new HashSet<Role>();
        for (Role role : Role.values()) {
            if (getRoles().get(role)) {
                roles.add(role);
            }
        }
        getRoleDao().assign(getUser().getId(), roles);
    }

    @Override
    public void validate() {
        if (isFormPresent()) {
            if (isChangePassword()) {
                validatePassword(getPassword(), getConfirmedPassword());
            }
        }
        super.validate();
    }

    protected void validatePassword(String password, String confirmedPassword) {
        if (password == null || password.isEmpty()) {
            addFieldError("password", getText("edit-form.error.password.empty"));
        } else if (!password.matches("^[\\s\\S]{6,}$")) {
            addFieldError("password", getText("edit-form.error.password.regex"));
        } else if (confirmedPassword == null || confirmedPassword.isEmpty()) {
            addFieldError("confirmedPassword", getText("edit-form.error.confirmed-password.empty"));
        } else if (!password.equals(confirmedPassword)) {
            addFieldError("confirmedPassword", getText("edit-form.error.confirmed-password.mismatch"));
        }
    }
}