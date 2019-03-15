package proxy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import sun.misc.ProxyGenerator;

/**
 * JDK原生动态代理是Java原生支持的，不需要任何外部依赖，但是它只能基于接口进行代理；
 * @author huangyejun
 *        
 */
public class DynamicProxyHandler extends TestProxy implements InvocationHandler
{
    
    private Object proxied;
    
    public DynamicProxyHandler(Object proxied)
    {
        this.proxied = proxied;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        // 将代理对象生成字节码到F盘上，方便反编译出java文件查看，实际动态代理是不需要自己生成的
        addClassToDisk(proxy.getClass().getName(), ProxyClassImpl.class, "E:/$Proxy0.class");
        System.out.println("method:" + method.getName());
        System.out.println("args:" + args[0].getClass().getName());
        System.out.println("Before invoke method...");
        Object object = method.invoke(proxied, args);
        System.out.println("After invoke method...");
        return object;
    }
    
    public Object newProxyInstance()
    {
        return Proxy.newProxyInstance(DynamicProxyHandler.class.getClassLoader(), // 需要使用的类加载器
                proxied.getClass().getInterfaces(), // 代理类实现的接口，可以有多个
                this);// 实际的处理对象
    }
    
    /**
     * 将动态生成的代理类输出到指定目录下
     * @param className
     * @param cl
     * @param path
     */
    private void addClassToDisk(String className, Class<?> cl, String path)
    {
        byte[] classFile = ProxyGenerator.generateProxyClass(className, cl.getInterfaces());
        
        FileOutputStream out = null;
        try
        {
            out = new FileOutputStream(path);
            // 将代理对象的class字节码写到硬盘上
            out.write(classFile);
            out.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
    }
    
    public static void main(String[] args)
    {
        ProxyClassImpl c = new ProxyClassImpl();
        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(c);
        IProxyClass proxyClass = (IProxyClass)proxyHandler.newProxyInstance();
        System.out.println(proxyClass.getClass().getName());
        System.out.println(proxyClass.sayHello("I love you"));
    }
    
    /**
     * 说明：
     * 代理对象是在程序运行时产生的，而不是编译期；
     * 对代理对象的所有接口方法调用都会转发到InvocationHandler.invoke()方法，
     * 在invoke()方法里我们可以加入任何逻辑，比如修改方法参数，加入日志功能、安全检查功能等；
     * 之后我们通过某种方式执行真正的方法体，示例中通过反射调用了Hello对象的相应方法，还可以通过RPC调用远程方法。
     * 
     * 注意1：对于从Object中继承的方法，
     * JDK Proxy会把hashCode()、equals()、toString()这三个非接口方法转发给InvocationHandler，其余的Object方法则不会转发
     */
}
