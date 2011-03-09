package org.hibernate.hv448.dummyservice;

import javax.validation.constraints.NotNull;

public class DummyBean
{
    @NotNull
    private String someProperty;
    
    public void setSomeProperty( String someProperty )
    {
        this.someProperty = someProperty;
    }
    
    public String getSomeProperty()
    {
        return someProperty;
    }

}
