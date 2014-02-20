package paulenka.aleh.wordbook.ui.action.user;

import java.sql.SQLException;

import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.dao.impl.UserDaoImpl;
import paulenka.aleh.wordbook.data.Registration;
import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.ui.action.common.ProcessFormAction;
import paulenka.aleh.wordbook.ui.interceptor.back.BackResultAction;
import paulenka.aleh.wordbook.ui.login.LoginManager;

@BackResultAction
public class RegisterUserAction extends ProcessFormAction {

    private static final long serialVersionUID = 1L;

    private LoginManager loginManager;
    private UserDao userDao;

    private Registration registration;

    protected LoginManager getLoginManager() {
        if (loginManager == null) {
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    protected UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @Override
    public String execute() throws Exception {
        if (getLoginManager().isLoggedIn()) {
            return SUCCESS;
        }
        return super.execute();
    }

    @Override
    public String view() {
        return INPUT;
    }

    @Override
    public String process() {
        try {
            User user = getUserDao().register(getRegistration());
            getLoginManager().login(user);
            return SUCCESS;
        } catch (SQLException ex) {
            return ERROR;
        }
    }

    @Override
    public void validate() {
        if (isFormPresent() && getRegistration() != null) {
            validateUsername(getRegistration().getUsername());
            validatePassword(getRegistration().getPassword(), getRegistration().getConfirmedPassword());
        }
    }

    protected void validateUsername(String username) {
        try {
            if (username == null || username.isEmpty()) {
                addFieldError("registration.username", getText("sign-up-form.error.username.empty"));
            } else if (!username.matches("^[a-zA-Z][a-zA-Z\\d]{2,11}$")) {
                addFieldError("registration.username", getText("sign-up-form.error.username.regex"));
            } else if (getUserDao().isUsernameExists(username)) {
                addFieldError("registration.username", getText("sign-up-form.error.username.already-exists"));
            }
        } catch (SQLException ex) {
            addActionError(ex.getMessage());
            ex.printStackTrace();
        }
    }

    protected void validatePassword(String password, String confirmedPassword) {
        if (password == null || password.isEmpty()) {
            addFieldError("registration.password", getText("sign-up-form.error.password.empty"));
        } else if (!password.matches("^[\\s\\S]{6,}$")) {
            addFieldError("registration.password", getText("sign-up-form.error.password.regex"));
        } else if (confirmedPassword == null || confirmedPassword.isEmpty()) {
            addFieldError("registration.confirmedPassword", getText("sign-up-form.error.confirmed-password.empty"));
        } else if (!password.equals(confirmedPassword)) {
            addFieldError("registration.confirmedPassword", getText("sign-up-form.error.confirmed-password.mismatch"));
        }
    }
}