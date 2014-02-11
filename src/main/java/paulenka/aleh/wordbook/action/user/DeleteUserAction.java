package paulenka.aleh.wordbook.action.user;

import java.sql.SQLException;

import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.interceptor.security.Authorization;

import com.opensymphony.xwork2.ActionSupport;

@Authorization
public class DeleteUserAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private UserDao userDao;

    private int id;

    protected UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String execute() {
        try {
            getUserDao().delete(getId());
            return SUCCESS;
        } catch (SQLException ex) {
            // TODO: Redirect to 500 page
            ex.printStackTrace();
            return ERROR;
        }
    }
}