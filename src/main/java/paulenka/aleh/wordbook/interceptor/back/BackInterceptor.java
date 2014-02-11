package paulenka.aleh.wordbook.interceptor.back;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import paulenka.aleh.wordbook.constant.HttpRequestMethod;
import paulenka.aleh.wordbook.constant.SessionAttribute;
import paulenka.aleh.wordbook.util.ActionAnnotationUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class BackInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    private static final String BACK_ATTRIBUTE = "back";
    private static final String DEFAULT_BACK_URI = "/";

    @SuppressWarnings("unchecked")
    protected Deque<String> getBackUriStack(ActionInvocation invocation) {
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        if (!session.containsKey(SessionAttribute.BACK_URI_STACK)) {
            session.put(SessionAttribute.BACK_URI_STACK, new ArrayDeque<>());
        }
        return (Deque<String>) session.get(SessionAttribute.BACK_URI_STACK);
    }

    protected boolean isBackAction(ActionInvocation invocation) {
        BackResultAction classAnnotation = ActionAnnotationUtil.getActionClassAnnotation(invocation, BackResultAction.class);
        BackResultAction methodAnnotation = ActionAnnotationUtil.getActionClassAnnotation(invocation, BackResultAction.class);

        return classAnnotation != null || methodAnnotation != null;
    }

    protected String getCurrentUri() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String requesrUri = request.getRequestURI().substring(request.getContextPath().length());
        if (request.getQueryString() != null) {
            requesrUri += '?' + request.getQueryString();
        }
        return requesrUri;
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        if (getBackUriStack(invocation).isEmpty()) {
            getBackUriStack(invocation).push(DEFAULT_BACK_URI);
        }

        invocation.getStack().set(BACK_ATTRIBUTE, getBackUriStack(invocation).getFirst());

        if (HttpRequestMethod.GET.equalsIgnoreCase(ServletActionContext.getRequest().getMethod())) {
            if (!isBackAction(invocation)) {
                getBackUriStack(invocation).clear();
            }

            getBackUriStack(invocation).remove(getCurrentUri());
            getBackUriStack(invocation).push(getCurrentUri());
        }

        String result = invocation.invoke();

        if (isBackAction(invocation) && Action.SUCCESS.equals(result)) {
            getBackUriStack(invocation).pop();
        }

        return result;
    }
}