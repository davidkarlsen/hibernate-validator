package org.hibernate.hv448.dummyservice;

import org.hibernate.hv448.ValidateMethod;
import org.springframework.stereotype.Service;



@Service
public class DummyServiceImpl
    implements DummyService
{

    @ValidateMethod
    public void doFailWithEmptyArg( String arg )
    {
    }

    @ValidateMethod
    public DummyBean doFailWithInvalidResult()
    {
        DummyBean dummyBean = new DummyBean();
        return dummyBean;
    }

    @ValidateMethod
    public void doFailWithNullArg( String arg ){}
    
    @ValidateMethod
    public DummyBean doFailWithNullResult()
    {
        return null;
    }

    @ValidateMethod
    public DummyBean proceedOkWithDefinedArg( String arg )
    {
        DummyBean dummyBean = new DummyBean();
        dummyBean.setSomeProperty( "someProperty" );
        return dummyBean;
    }
    
    

}
