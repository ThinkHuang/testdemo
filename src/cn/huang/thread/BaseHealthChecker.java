package thread;

import java.util.concurrent.CountDownLatch;

public abstract class BaseHealthChecker implements Runnable
{
    
    private CountDownLatch latch;
    
    private String serviceName;
    
    public BaseHealthChecker(CountDownLatch _latch, String _serviceName)
    {
        this.latch = _latch;
        this.serviceName = _serviceName;
    }
    
    public boolean isServiceUp()
    {
        return false;
    }
    
    public CountDownLatch getLatch()
    {
        return latch;
    }
    
    public void setLatch(CountDownLatch latch)
    {
        this.latch = latch;
    }
    
    public String getServiceName()
    {
        return serviceName;
    }
    
    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public abstract void executeService();
    
}