package intro_to_spring_user_system.user.annotations.password;


import intro_to_spring_user_system.user.constants.TextConstants;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default TextConstants.INVALID_PASSWORD_FORMAT;

    int minLength() default 6;

    int maxLength() default 30;

    boolean containsDigit() default false;

    boolean containsLowerCase() default false;

    boolean containsUpperCase() default false;

    boolean containsSpecialSymbols() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
