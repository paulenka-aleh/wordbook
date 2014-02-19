package paulenka.aleh.wordbook.ui.interceptor.security;

import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.ui.login.LoginManager;
import paulenka.aleh.wordbook.ui.util.ActionAnnotationUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthenticationInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    private LoginManager loginManager;

    protected LoginManager getLoginManager() {
        if (loginManager == null) {
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    protected boolean isAuthenticationRequired(ActionInvocation invocation) throws NoSuchMethodException {
        Authorization actionClassAnnotation = ActionAnnotationUtil.getActionClassAnnotation(invocation, Authorization.class);
        Authorization actionMethodAnnotation = ActionAnnotationUtil.getActionMethodAnnotation(invocation, Authorization.class);

        return actionClassAnnotation != null || actionMethodAnnotation != null;
    }

    protected User getAutenticatedUser(ActionInvocation invocation) {
        return getLoginManager().getUser();
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (isAuthenticationRequired(invocation) && getAutenticatedUser(invocation) == null) {
            return Action.LOGIN;
        } else {
            return invocation.invoke();
        }
    }
}