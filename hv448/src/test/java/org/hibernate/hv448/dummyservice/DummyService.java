package org.hibernate.hv448.dummyservice;

public interface DummyService
{
    
    void doFailWithNullArg( String arg );
    
    void doFailWithEmptyArg( String arg );
    
    DummyBean doFailWithInvalidResult();
    
    DummyBean doFailWithNullResult();
    
    DummyBean proceedOkWithDefinedArg( String arg );

}
