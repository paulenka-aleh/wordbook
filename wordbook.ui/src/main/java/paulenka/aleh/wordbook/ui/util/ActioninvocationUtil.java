package paulenka.aleh.wordbook.ui.util;

import java.lang.annotation.Annotation;

import com.opensymphony.xwork2.ActionInvocation;

public final class ActioninvocationUtil {

    private ActioninvocationUtil() {
    }

    public static <T extends Annotation> T getActionClassAnnotation(ActionInvocation invocation, Class<T> annotation) {
        return getActionClass(invocation).getAnnotation(annotation);
    }

    public static <T extends Annotation> T getActionMethodAnnotation(ActionInvocation invocation, Class<T> annotation) {
        try {
            return getActionClass(invocation).getMethod(invocation.getProxy().getMethod()).getAnnotation(annotation);
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    public static Class<?> getActionClass(ActionInvocation invocation) {
        return invocation.getAction().getClass();
    }
}
