package proxy;

public class ProxyClassImpl implements IProxyClass
{
    
    @Override
    public String sayHello(String s)
    {
        return "HelloImpl " + s;
    }
    
}
