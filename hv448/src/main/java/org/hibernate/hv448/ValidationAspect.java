package org.hibernate.hv448;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidatorFactory;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.method.MethodValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A validation aspect which uses JSR-303 extensions, specifically the 
 * <a href="https://cwiki.apache.org/BeanValidation/">Apache BVF framework</a> to apply method validation.
 * 
 * @author et2448
 */
@Aspect
public class ValidationAspect
{
    private final Logger logger = LoggerFactory.getLogger( getClass() );
    private final ValidatorFactory validatorFactory;
    private int order;

    /**
     * The {@linkplain ValidatorFactory} for validating parameters and return values. Note: This must be an extended
     * implementation, as plain JSR303 does not support method and/or return value validation.
     * 
     * @param validatorFactory
     */
    public ValidationAspect( ValidatorFactory validatorFactory )
    {
        this.validatorFactory = validatorFactory;
    }

    private MethodValidator getMethodValidator()
    {
        return validatorFactory.getValidator().unwrap( MethodValidator.class );
    }
    
    /**
     * Defines the aspect order.
     * @see {@linkplain Ordered#getOrder()}.
     */
    public int getOrder()
    {
        return order;
    }
    
    /**
     * Define the aspect order.
     * @param order
     */
    public void setOrder( int order )
    {
        this.order = order;
    }
    
    @Pointcut( "execution( public * *(..) )" )
    public void anyPublicMethodExecution(){}
    
    /**
     * Identifies method executions which do not return results, e.g. void methods.
     */
    @Pointcut( "execution( public !void *(..) )" )
    public void anyNonVoidMethodExecution(){}
    
    @Pointcut( value="@annotation(validateMethod)", argNames="validateMethod" )
    public void isAnnotated( ValidateMethod validateMethod ){}

    /**
     * Validates method invocations.
     * 
     * @param joinPoint
     * @throws ConstraintViolationException
     */
    @Before( value="anyPublicMethodExecution() && isAnnotated(validateMethod)" )
    public void validate( JoinPoint joinPoint, ValidateMethod validateMethod )
        throws ConstraintViolationException, NoSuchMethodException
    {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Object target = joinPoint.getTarget();
        Method method = target.getClass().getDeclaredMethod( methodSignature.getName(), methodSignature.getParameterTypes() );
        Object[] args = joinPoint.getArgs();
        Class<?>[] groups = validateMethod.entryGroups();

        logger.trace( "Validating method {} on target {} passed args {} and {}", new Object[] { method, target, args, validateMethod } );

        MethodValidator methodValidator = getMethodValidator();
        Set<? extends ConstraintViolation<?>> validationErrors =
            methodValidator.validateAllParameters( target, method, args, groups );
        if ( !validationErrors.isEmpty() )
        {
            throw new ConstraintViolationException( new HashSet<ConstraintViolation<?>>( validationErrors ) );
        }
    }

    /**
     * Validates return values
     * 
     * @param joinPoint
     * @param returnedValue
     * @throws ConstraintViolationException
     */
    @AfterReturning( pointcut = "anyNonVoidMethodExecution() && isAnnotated(validateMethod)", returning = "returnedValue" )
    public void validateReturnedValue( JoinPoint joinPoint, Object returnedValue, ValidateMethod validateMethod )
        throws ConstraintViolationException, NoSuchMethodException
    {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();


        Object target = joinPoint.getTarget();
        Method method = target.getClass().getDeclaredMethod( methodSignature.getName(), methodSignature.getParameterTypes() );        
        logger.trace( "Validating return value {} from method {} on target {} {}", new Object[] { returnedValue, method,
            target, validateMethod } );
        Class<?>[] groups = validateMethod.returnGroups();

        Set<? extends ConstraintViolation<?>> validationErrors =
            getMethodValidator().validateReturnValue( target, method, returnedValue, groups );

        if ( !validationErrors.isEmpty() )
        {
            throw new ConstraintViolationException( new HashSet<ConstraintViolation<?>>( validationErrors ) );
        }
    }

}
