package org.hibernate.hv448;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.groups.Default;

/**
 * Denotes a method to be validated
 * 
 * @author et2448
 */
@Documented
@Retention( RUNTIME )
@Target( METHOD )
@Inherited
public @interface ValidateMethod
{
    /**
     * The validation groups that apply on method entry.
     * Default is {@linkplain Default}.
     * @return the groups to validate.
     */
    Class<?>[] entryGroups() default { Default.class };
    
    /**
     * The validation groups that apply on method return.
     * Default is {@linkplain Default}.
     * @return the groups to validate.
     */
    Class<?>[] returnGroups() default { Default.class };
    
}
