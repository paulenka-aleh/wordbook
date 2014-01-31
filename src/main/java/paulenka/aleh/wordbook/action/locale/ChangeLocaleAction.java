package paulenka.aleh.wordbook.action.locale;

import java.util.Locale;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.I18nInterceptor;

public class ChangeLocaleAction extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 1L;

    private final static String SUCCESS = "success";

    private final static String DEFAULT_FORWARD_URI = "/";

    private Map<String, Object> session;

    private String language;
    private String redirectUri;

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

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUrl) {
        this.redirectUri = redirectUrl;
    }

    @Override
    public String execute() throws Exception {
        if (getLanguage() != null) {
            Locale locale = new Locale(getLanguage());
            getSession().put(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, locale);
        }
        if (getRedirectUri() == null || getRedirectUri().isEmpty()) {
            setRedirectUri(DEFAULT_FORWARD_URI);
        }
        return SUCCESS;
    }
}