package paulenka.aleh.wordbook.action.user;

import paulenka.aleh.wordbook.dao.UserDao;

import com.opensymphony.xwork2.ActionSupport;

//@Secure(roles = AuthorizationRole.ADMINISTRATOR)
public class EditUserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private UserDao userDao;

	protected UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	@Override
	public String execute() {
		return SUCCESS;
	}
}