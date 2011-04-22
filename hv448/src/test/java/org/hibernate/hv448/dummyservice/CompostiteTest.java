package org.hibernate.hv448.dummyservice;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.impl.SizeValidatorForString;

@Constraint( validatedBy=SizeValidatorForString.class )
@Retention( RUNTIME )
@Documented
@Target( { PARAMETER, FIELD, ANNOTATION_TYPE, CONSTRUCTOR } )
@Size( min=2, max=25 )
public @interface CompostiteTest
{
    String message() default "{org.hibernate.hv448.dummyservice.CompositeTest.message}";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
