package paulenka.aleh.wordbook.util;

import java.lang.annotation.Annotation;

import com.opensymphony.xwork2.ActionInvocation;

public final class ActionAnnotationUtil {

    private ActionAnnotationUtil() {
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

    private static Class<?> getActionClass(ActionInvocation invocation) {
        return invocation.getAction().getClass();
    }
}
