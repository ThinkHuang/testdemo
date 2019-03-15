package jmx;

public class HelloWorld implements HelloWorldMBean
{
    
    private String greeting;
    
    public HelloWorld(String greeting)
    {
        this.greeting = greeting;
    }
    
    @Override
    public String getGreeting()
    {
        return "Hello World";
    }
    
    @Override
    public void setGreeting(String greeting)
    {
        this.greeting = greeting;
    }
    
    @Override
    public void printGreeting()
    {
        System.out.println(this.greeting);
    }
    
}
