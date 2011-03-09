package org.hibernate.hv448;

import javax.validation.ConstraintViolationException;

import org.hibernate.hv448.dummyservice.DummyBean;
import org.hibernate.hv448.dummyservice.DummyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ValidationAspectTest
{
    @Autowired
    private DummyService dummyService;
    
    @Test( expected=ConstraintViolationException.class )
    public void doFailWithEmptyArg() {
        dummyService.doFailWithEmptyArg( "" );
    }
    
    @Test( expected=ConstraintViolationException.class )
    public void doFailWithInvalidResult() {
        dummyService.doFailWithInvalidResult();
    }
    
    @Test( expected=ConstraintViolationException.class )
    public void doFailWithNullArg() {
        dummyService.doFailWithNullArg( null );
    }
    
    @Test( expected=ConstraintViolationException.class )
    public void doFailWithNullResult() {
        dummyService.doFailWithNullResult();
    }
    
    @Test
    public void proceedOkWithDefinedArg() {
        DummyBean dummyBean = dummyService.proceedOkWithDefinedArg( "someValue" );
        Assert.notNull( dummyBean );
        Assert.notNull( dummyBean.getSomeProperty() );
    }

}
