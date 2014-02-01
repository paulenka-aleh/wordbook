package paulenka.aleh.wordbook.action;

import com.opensymphony.xwork2.ActionSupport;

public class RedirectBackAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    public final static String REDIRECT_URI_PARAM = "redirectUri";

    private final static String DEFAULT_REDIRECT_URI = "/";

    private String redirectUri;

    public String getRedirectUri() {
        return (redirectUri != null && !redirectUri.isEmpty()) ? redirectUri : DEFAULT_REDIRECT_URI;
    }

    public void setRedirectUri(String redirectUrl) {
        this.redirectUri = redirectUrl;
    }
}