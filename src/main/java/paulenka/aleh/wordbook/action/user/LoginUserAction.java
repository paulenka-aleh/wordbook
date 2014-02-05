package paulenka.aleh.wordbook.action.user;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import paulenka.aleh.wordbook.constant.SessionAttribute;
import paulenka.aleh.wordbook.dao.UserDao;
import paulenka.aleh.wordbook.entity.Credentials;
import paulenka.aleh.wordbook.entity.User;
import paulenka.aleh.wordbook.interceptor.back.BackResultAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

@BackResultAction
public class LoginUserAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;

	private UserDao userDao;

	private Credentials credentials;

	@Inject
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
		if (getSession().containsKey(SessionAttribute.USER)) {
			return SUCCESS;
		}
		if (getCredentials() == null || (getCredentials().getUsername() == null || getCredentials().getUsername().isEmpty()) &&
				(getCredentials().getPassword() == null || getCredentials().getPassword().isEmpty())) {
			return INPUT;
		}
		try {
			User user = getUserDao().login(getCredentials());
			if (user != null) {
				getSession().put(SessionAttribute.USER, user);
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