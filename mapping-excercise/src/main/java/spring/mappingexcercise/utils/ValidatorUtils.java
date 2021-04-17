package spring.mappingexcercise.utils;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtils {

    <E> boolean isValid(E entity);

    <E> Set<ConstraintViolation<E>> violations(E entity);
}
