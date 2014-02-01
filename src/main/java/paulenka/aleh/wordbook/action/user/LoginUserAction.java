package paulenka.aleh.wordbook.action.user;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import paulenka.aleh.wordbook.action.RedirectBackAction;
import paulenka.aleh.wordbook.constant.SessionAttributes;
import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.entity.Credentials;
import paulenka.aleh.wordbook.entity.User;

public class LoginUserAction extends RedirectBackAction implements SessionAware {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> session;

    private UserDao userDao;

    private Credentials credentials;

    protected UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao();
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

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public String execute() {
        if (getSession().containsKey(SessionAttributes.USER)) {
            return SUCCESS;
        }
        if (getCredentials() == null || (getCredentials().getUsername() == null || getCredentials().getUsername().isEmpty()) &&
                (getCredentials().getPassword() == null || getCredentials().getPassword().isEmpty())) {
            return INPUT;
        }
        try {
            User user = getUserDao().login(getCredentials());
            if (user != null) {
                getSession().put(SessionAttributes.USER, user);
                return SUCCESS;
            } else {
                addActionError(getText("sign-in-form.error"));
                return INPUT;
            }
        } catch (SQLException ex) {
            // TODO: Redirect to 500 page
            ex.printStackTrace();
            return INPUT;
        }
    }
}