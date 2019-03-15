package jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * 访问方式为在浏览器中输入http://localhost:9092即可访问到MBean管理页面
 * @author wrd
 *
 */
public class HelloAgent implements NotificationListener
{
    private MBeanServer mbs;
    
    public HelloAgent()
    {
        this.mbs = MBeanServerFactory.createMBeanServer("HelloAgent");
        HelloWorld hw = new HelloWorld("Agent");
        try
        {
            ObjectName helloWorldName = new ObjectName("HelloAgent:name=helloWorld");
            mbs.registerMBean(hw, helloWorldName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        startHtmlAdaptorServer();
        
    }
    
    
    
    private void startHtmlAdaptorServer()
    {
        HtmlAdaptorServer htmlAdaptorServer = new HtmlAdaptorServer();
        ObjectName adapterName = null;
        try {
            // 多个属性使用,分隔
            adapterName = new ObjectName("HelloAgent:name=htmladapter,port=9092");
            htmlAdaptorServer.setPort(9092);
            mbs.registerMBean(htmlAdaptorServer, adapterName);
            htmlAdaptorServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void handleNotification(Notification notification, Object handback)
    {
        
    }
    
    public static void main(String args[]){
        System.out.println(" hello agent is running");
        new HelloAgent();
    }

}
