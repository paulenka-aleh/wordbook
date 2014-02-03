package paulenka.aleh.wordbook.action.user;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import paulenka.aleh.wordbook.constant.SessionAttributes;
import paulenka.aleh.wordbook.interceptor.back.BackResultAction;
import paulenka.aleh.wordbook.interceptor.security.Authorization;

import com.opensymphony.xwork2.ActionSupport;

@Authorization
@BackResultAction
public class LogoutUserAction extends ActionSupport implements SessionAware {

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