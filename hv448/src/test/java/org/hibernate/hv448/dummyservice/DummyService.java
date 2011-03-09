package org.hibernate.hv448.dummyservice;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public interface DummyService
{
    
    void doFailWithNullArg( @NotNull String arg );
    
    void doFailWithEmptyArg( @NotEmpty String arg );
    
    DummyBean doFailWithInvalidResult();
    
    DummyBean doFailWithNullResult();
    
    DummyBean proceedOkWithDefinedArg( @NotEmpty String arg );

}
