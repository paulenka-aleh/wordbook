package paulenka.aleh.wordbook.action.user;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import paulenka.aleh.wordbook.action.RedirectBackAction;
import paulenka.aleh.wordbook.constant.SessionAttributes;

public class LogoutUserAction extends RedirectBackAction implements SessionAware {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public String execute() {
		getSession().remove(SessionAttributes.USER);
		return SUCCESS;
	}
}