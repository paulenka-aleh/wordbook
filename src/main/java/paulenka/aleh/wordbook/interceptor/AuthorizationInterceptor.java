package paulenka.aleh.wordbook.interceptor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import paulenka.aleh.wordbook.action.RedirectBackAction;
import paulenka.aleh.wordbook.constant.AuthorizationRole;
import paulenka.aleh.wordbook.constant.GlobalActionResults;
import paulenka.aleh.wordbook.constant.SessionAttributes;
import paulenka.aleh.wordbook.dao.RoleDao;
import paulenka.aleh.wordbook.entity.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorizationInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    private RoleDao roleDao;

    protected RoleDao getRoleDao() {
        if (roleDao == null) {
            roleDao = new RoleDao();
        }
        return roleDao;
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (isSecure(invocation)) {
            if (getAutenticatedUser(invocation) != null) {
                if (isAuthorized(invocation)) {
                    return invocation.invoke();
                } else {
                    ServletActionContext.getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
                    return ActionSupport.NONE;
                }
            } else {
                invocation.getStack().set(RedirectBackAction.REDIRECT_URI_PARAM, getReirectUrl());
                return GlobalActionResults.LOGIN;
            }
        } else {
            return invocation.invoke();
        }
    }

    private String getReirectUrl() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request.getRequestURI().substring(request.getContextPath().length());
    }

    protected boolean isAuthorized(ActionInvocation invocation) throws SQLException {
        List<AuthorizationRole> authorizedRoles = getAuthorizedRoles(invocation);
        List<AuthorizationRole> userRoles = getRoleDao().getUserRoles(getAutenticatedUser(invocation).getId());

        List<AuthorizationRole> authorizedToAction = new ArrayList<>(authorizedRoles);
        authorizedToAction.retainAll(userRoles);

        return authorizedRoles.isEmpty() || !authorizedToAction.isEmpty();
    }

    protected User getAutenticatedUser(ActionInvocation invocation) {
        return (User) invocation.getInvocationContext().getSession().get(SessionAttributes.USER);
    }

    protected List<AuthorizationRole> getAuthorizedRoles(ActionInvocation invocation) {
        Secure actionClassSecure = getActionClassSecure(invocation);
        Secure actionMethodSecure = getMethodClassSecure(invocation);

        if (actionMethodSecure != null) {
            return Arrays.asList(actionMethodSecure.roles());
        } else if (actionClassSecure != null) {
            return Arrays.asList(actionClassSecure.roles());
        } else {
            return Collections.emptyList();
        }
    }

    protected boolean isSecure(ActionInvocation invocation) {
        return getActionClassSecure(invocation) != null || getMethodClassSecure(invocation) != null;
    }

    private Secure getActionClassSecure(ActionInvocation invocation) {
        return getActionClass(invocation).getAnnotation(Secure.class);
    }

    private Secure getMethodClassSecure(ActionInvocation invocation) {
        try {
            return getActionClass(invocation).getMethod(invocation.getProxy().getMethod()).getAnnotation(Secure.class);
        } catch (NoSuchMethodException | SecurityException ex) {
            return null;
        }
    }

    private Class<?> getActionClass(ActionInvocation invocation) {
        return invocation.getAction().getClass();
    }
}