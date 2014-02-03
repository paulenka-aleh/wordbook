package paulenka.aleh.wordbook.interceptor.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import paulenka.aleh.wordbook.constant.AuthorizationRole;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.METHOD })
public @interface Authorization {
    AuthorizationRole[] roles() default {};
}