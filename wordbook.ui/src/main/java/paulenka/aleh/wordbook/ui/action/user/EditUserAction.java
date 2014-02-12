package paulenka.aleh.wordbook.ui.action.user;

import java.sql.SQLException;

import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.dao.impl.UserDaoImpl;
import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;

import com.opensymphony.xwork2.ActionSupport;

@Authorization
public class EditUserAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private UserDao userDao;

    private User user;

    protected UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String execute() {
        try {
            setUser(getUserDao().get(getUser().getId()));
            return SUCCESS;
        } catch (SQLException ex) {
            // TODO: Redirect to 500 page
            ex.printStackTrace();
            return ERROR;
        }
    }
}