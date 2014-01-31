package paulenka.aleh.wordbook.action.locale;

import java.util.Locale;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import paulenka.aleh.wordbook.action.RedirectBackAction;

import com.opensymphony.xwork2.interceptor.I18nInterceptor;

public class ChangeLocaleAction extends RedirectBackAction implements SessionAware {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;

	private String language;

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String execute() throws Exception {
		if (getLanguage() != null) {
			Locale locale = new Locale(getLanguage());
			getSession().put(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, locale);
		}
		return SUCCESS;
	}
}