package paulenka.aleh.wordbook.ui.action.user;

import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.dao.impl.UserDaoImpl;
import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;

import com.opensymphony.xwork2.ActionSupport;

@Authorization
public class ListUserAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private UserDao userDao;

    private List<User> users;

    protected UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String execute() {
        try {
            setUsers(getUserDao().list());
            return INPUT;
        } catch (SQLException ex) {
            // TODO: Redirect to 500 page
            ex.printStackTrace();
            return ERROR;
        }
    }
}