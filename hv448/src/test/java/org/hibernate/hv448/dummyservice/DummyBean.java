package org.hibernate.hv448.dummyservice;


public class DummyBean
{
    @CompostiteTest
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
