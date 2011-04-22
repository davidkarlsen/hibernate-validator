package org.hibernate.hv448.dummyservice;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public interface DummyService
{
    
    void doFailWithNullArg( @NotNull String arg );
    
    void doFailWithEmptyArg( @NotEmpty String arg );
    
    @Valid DummyBean doFailWithInvalidResult();
    
    @NotNull DummyBean doFailWithNullResult();
    
    @Valid DummyBean proceedOkWithDefinedArg( @NotEmpty String arg );

}
