package paulenka.aleh.wordbook.ui.action.user;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.dao.impl.UserDaoImpl;
import paulenka.aleh.wordbook.data.Registration;
import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.ui.constant.SessionAttribute;
import paulenka.aleh.wordbook.ui.interceptor.back.BackResultAction;

import com.opensymphony.xwork2.ActionSupport;

@BackResultAction
public class RegisterUserAction extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> session;

    private UserDao userDao;

    private Registration registration;

    protected UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @Override
    public String execute() {
        if (getSession().containsKey(SessionAttribute.USER)) {
            return SUCCESS;
        }
        if (getRegistration() == null) {
            return INPUT;
        }
        try {
            User user = getUserDao().register(getRegistration());
            getSession().put(SessionAttribute.USER, user);
            return SUCCESS;
        } catch (SQLException ex) {
            // TODO: forward to 500 page
            ex.printStackTrace();
            return ERROR;
        }
    }

    @Override
    public void validate() {
        if (getRegistration() != null) {
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
            } else if (getUserDao().isUserExists(username)) {
                addFieldError("registration.username", getText("sign-up-form.error.username.already-exists"));
            }
        } catch (SQLException ex) {
            // TODO: forward to 500 page
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