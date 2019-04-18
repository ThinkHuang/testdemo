package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * 首先声明一个观点，那就是为什么Object的wait()，notify()和notifyAll()必须要放到同步代码块中，
 * 我的理解是，由于释放cpu资源和发送中断信号去唤醒等待的线程必须是原子性的操作的，而同步代码块提供了这种环境。
 * 另外，这个问题引出了一个Lost Wake-up problem，这个问题也是由于操作非原子性，而希望获得的结果是在原子级别的操作下产生的。
 *
 */
public class LostWakeUpProblem
{
    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    /**
     * 该方法将抛出IllegalMonitorStateException
     */
    @Test
    public void test() {
        try {
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Object obj = new Object();

    private Object anotherObj = new Object();

    /**
     * 该方法由于同步加锁的对象和释放的对象不是同一个，同样会抛出IllegalMonitorStateException
     */
    @Test
    public void produce() {
        synchronized (obj) {
            try {
                anotherObj.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
