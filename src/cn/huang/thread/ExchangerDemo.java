package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 该类的主要功能是测试Exchanger
 * Exchanger的作用：在两个线程之间进行数据交换，线程会阻塞在Exchanger的exchange的方法上
 * 直到另外一个线程也到了同一个Exchanger的exchange方法时，二者进行数据交换，交换的数据在exchange的参数中
 * @author huangyejun
 *
 */
public class ExchangerDemo
{
    
    public static void main(String[] args)
    {
        final Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
        new Thread(){
            @Override
            public void run()
            {
                List<Integer> list = new ArrayList<Integer>(2);
                list.add(1);
                list.add(2);
                try
                {
                    list = exchanger.exchange(list);
                }
                catch (Exception e)
                {
                    // TODO: handle exception
                }
                System.out.println("thread1 :" + list);
            }
        }.start();
        
        new Thread(){
            @Override
            public void run()
            {
                List<Integer> list = new ArrayList<Integer>(2);
                list.add(4);
                list.add(5);
                try
                {
                    list = exchanger.exchange(list);
                }
                catch (Exception e)
                {
                    // TODO: handle exception
                }
                System.out.println("thread2 :" + list);
            }
        }.start();
    }
}
