package cn.huang.jmx;

public interface HelloWorldMBean
{
    
    String getGreeting();
    
    void setGreeting(String greeting);
    
    void printGreeting();
}
