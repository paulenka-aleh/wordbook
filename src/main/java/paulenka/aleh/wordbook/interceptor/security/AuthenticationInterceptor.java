package paulenka.aleh.wordbook.interceptor.security;

import java.util.Map;

import paulenka.aleh.wordbook.constant.GlobalActionResults;
import paulenka.aleh.wordbook.constant.SessionAttributes;
import paulenka.aleh.wordbook.entity.User;
import paulenka.aleh.wordbook.util.ActionAnnotationUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthenticationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	protected boolean isAuthenticationRequired(ActionInvocation invocation) throws NoSuchMethodException {
		Authorization actionClassAnnotation = ActionAnnotationUtil.getActionClassAnnotation(invocation, Authorization.class);
		Authorization actionMethodAnnotation = ActionAnnotationUtil.getActionMethodAnnotation(invocation, Authorization.class);

		return actionClassAnnotation != null || actionMethodAnnotation != null;
	}

	protected User getAutenticatedUser(ActionInvocation invocation) {
		return (User) invocation.getInvocationContext().getSession().get(SessionAttributes.USER);
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<?, ?> application = (Map<?, ?>) ActionContext.getContext().get("application");
		System.out.println(application.get("javax.servlet.context.tempdir"));

		if (isAuthenticationRequired(invocation) && getAutenticatedUser(invocation) == null) {
			return GlobalActionResults.LOGIN;
		} else {
			return invocation.invoke();
		}
	}
}