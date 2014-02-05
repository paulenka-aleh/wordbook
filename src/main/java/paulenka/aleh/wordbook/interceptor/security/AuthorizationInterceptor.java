package paulenka.aleh.wordbook.interceptor.security;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import paulenka.aleh.wordbook.constant.AuthorizationRole;
import paulenka.aleh.wordbook.constant.SessionAttribute;
import paulenka.aleh.wordbook.dao.RoleDao;
import paulenka.aleh.wordbook.entity.User;
import paulenka.aleh.wordbook.util.ActionAnnotationUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
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

	protected User getAutenticatedUser(ActionInvocation invocation) {
		return (User) invocation.getInvocationContext().getSession().get(SessionAttribute.USER);
	}

	protected boolean isUserAuthorizedForAction(ActionInvocation invocation) throws SQLException, NoSuchMethodException {
		List<AuthorizationRole> authorizedRoles = getAuthorizedRoles(invocation);
		List<AuthorizationRole> userRoles = getRoleDao().getUserRoles(getAutenticatedUser(invocation).getId());

		List<AuthorizationRole> authorizedToAction = new ArrayList<>(authorizedRoles);
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

	protected List<AuthorizationRole> getAuthorizedRoles(ActionInvocation invocation) throws NoSuchMethodException {
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