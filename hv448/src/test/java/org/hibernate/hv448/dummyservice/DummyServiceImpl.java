package org.hibernate.hv448.dummyservice;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.hv448.ValidateMethod;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;



@Service
public class DummyServiceImpl
    implements DummyService
{

    @ValidateMethod
    public void doFailWithEmptyArg( @NotEmpty String arg )
    {
    }

    @ValidateMethod
    public @Valid DummyBean doFailWithInvalidResult()
    {
        DummyBean dummyBean = new DummyBean();
        return dummyBean;
    }

    @ValidateMethod
    public void doFailWithNullArg( @NotNull String arg ){}
    
    @ValidateMethod
    public @NotNull DummyBean doFailWithNullResult()
    {
        return null;
    }

    @ValidateMethod
    public @Valid DummyBean proceedOkWithDefinedArg( @NotEmpty String arg )
    {
        DummyBean dummyBean = new DummyBean();
        dummyBean.setSomeProperty( "someProperty" );
        return dummyBean;
    }
    
    

}
