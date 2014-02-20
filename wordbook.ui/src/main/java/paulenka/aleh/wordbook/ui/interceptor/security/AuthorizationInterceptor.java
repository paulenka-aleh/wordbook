package paulenka.aleh.wordbook.ui.interceptor.security;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.data.User;
import paulenka.aleh.wordbook.ui.login.LoginManager;
import paulenka.aleh.wordbook.ui.util.ActionAnnotationUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorizationInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    private LoginManager loginManager;

    protected LoginManager getLoginManager() {
        if (loginManager == null) {
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    protected User getAutenticatedUser(ActionInvocation invocation) {
        return getLoginManager().getUser();
    }

    protected boolean isUserAuthorizedForAction(ActionInvocation invocation) throws SQLException, NoSuchMethodException {
        List<Role> authorizedRoles = getAuthorizedRoles(invocation);
        Set<Role> userRoles = getLoginManager().getRoles();

        List<Role> authorizedToAction = new ArrayList<>(authorizedRoles);
        authorizedToAction.retainAll(userRoles);

        return authorizedRoles.isEmpty() || !authorizedToAction.isEmpty();
    }

    protected boolean isAuthenticationRequired(ActionInvocation invocation) throws NoSuchMethodException {
        Authorization actionClassAnnotation = ActionAnnotationUtil.getActionClassAnnotation(invocation, Authorization.class);
        Authorization actionMethodAnnotation = ActionAnnotationUtil.getActionMethodAnnotation(invocation, Authorization.class);

        return actionClassAnnotation != null || actionMethodAnnotation != null;
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (isAuthenticationRequired(invocation) && !isUserAuthorizedForAction(invocation)) {
            ServletActionContext.getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
            return Action.NONE;
        } else {
            return invocation.invoke();
        }
    }

    protected List<Role> getAuthorizedRoles(ActionInvocation invocation) throws NoSuchMethodException {
        Authorization actionClassAnnotation = ActionAnnotationUtil.getActionClassAnnotation(invocation, Authorization.class);
        Authorization actionMethodAnnotation = ActionAnnotationUtil.getActionMethodAnnotation(invocation, Authorization.class);

        if (actionMethodAnnotation != null) {
            return Arrays.asList(actionMethodAnnotation.roles());
        } else if (actionClassAnnotation != null) {
            return Arrays.asList(actionClassAnnotation.roles());
        } else {
            return Collections.emptyList();
        }
    }
}