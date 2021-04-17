package intro_to_spring_user_system.user.annotations;

import javax.validation.ConstraintValidatorContext;

public class AnnotationsUtil {
    private AnnotationsUtil() {
    }

    public static void setErrorMessage(final ConstraintValidatorContext context, final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }

}
