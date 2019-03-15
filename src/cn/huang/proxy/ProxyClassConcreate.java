package proxy;

/**
 * 使用CGLIB完成动态代理
 * @author huangyejun
 *
 */
public class ProxyClassConcreate
{
    public String sayHello(String str) {
        return "ProxyClassConcreate: " + str;
    }
}
