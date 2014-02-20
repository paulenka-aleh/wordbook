package paulenka.aleh.wordbook.ui.interceptor.security;

import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.ui.login.LoginManager;
import paulenka.aleh.wordbook.ui.util.ActioninvocationUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthenticationInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    private static final String AJAX_LOGIN = "ajax-login";
    private static final String AJAX_LOGIN_ATTRIBUTE = "login";
    private static final Object AJAX_LOGIN_INDICATOR = "login";

    private LoginManager loginManager;

    protected LoginManager getLoginManager() {
        if (loginManager == null) {
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    protected boolean isAuthenticationRequired(ActionInvocation invocation) throws NoSuchMethodException {
        Authorization actionClassAnnotation = ActioninvocationUtil.getActionClassAnnotation(invocation, Authorization.class);
        Authorization actionMethodAnnotation = ActioninvocationUtil.getActionMethodAnnotation(invocation, Authorization.class);

        return actionClassAnnotation != null || actionMethodAnnotation != null;
    }

    protected User getAutenticatedUser(ActionInvocation invocation) {
        return getLoginManager().getUser();
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (isAuthenticationRequired(invocation) && getAutenticatedUser(invocation) == null) {
            if (ActioninvocationUtil.getActionClassAnnotation(invocation, JsonActionResult.class) != null) {
                invocation.getInvocationContext().getValueStack().set(AJAX_LOGIN_ATTRIBUTE, AJAX_LOGIN_INDICATOR);
                return AJAX_LOGIN;
            } else {
                return Action.LOGIN;
            }
        } else {
            return invocation.invoke();
        }
    }
}