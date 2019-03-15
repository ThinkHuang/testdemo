package thread;

public abstract class BaseHealthChecker
{
    
    private CountDownLatch _latch;
    private String _serviceName;
    
    public BaseHealthChecker(CountDownLatch _latch, String _serviceName)
    {
        this._latch = _latch;
        this._serviceName = _serviceName;
    }
    
    protected abstract void executeService();
    
    
}