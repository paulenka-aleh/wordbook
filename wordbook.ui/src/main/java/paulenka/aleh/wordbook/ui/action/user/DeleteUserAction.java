package paulenka.aleh.wordbook.ui.action.user;

import java.sql.SQLException;

import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.dao.impl.UserDaoImpl;
import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.ui.action.common.ProcessFormAction;
import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;

@Authorization(roles = { Role.ADMINISTRATOR })
public class DeleteUserAction extends ProcessFormAction {

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
    public String view() {
        return INPUT;
    }

    @Override
    public String process() {
        try {
            getUserDao().delete(getUser().getId());
            return SUCCESS;
        } catch (SQLException ex) {
            return ERROR;
        }
    }
}