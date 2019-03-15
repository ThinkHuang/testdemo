package org.huang.proxy;

import java.lang.reflect.Method;
import java.util.Arrays;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * CGLIB通过继承的方式进行代理，无论目标对象有没有实现接口都可以代理，但是无法处理final的情况
 * @author huangyejun
 *
 */
public class MyMethodInterceptor implements MethodInterceptor
{

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable
    {
        System.out.println("you say:" + Arrays.toString(args));
        return proxy.invokeSuper(obj, args);
    }
    
    
    public static void main(String[] args)
    {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyClassConcreate.class);
        enhancer.setCallback(new MyMethodInterceptor());
        ProxyClassConcreate concreate = (ProxyClassConcreate)enhancer.create();
        System.out.println(concreate.sayHello("I love you!"));
    }

}
