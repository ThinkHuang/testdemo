package concurrency;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    
    private List<String> list;
    
    private CountDownLatch latch;
    
    private List<String> totalList;
    
    public Worker(CountDownLatch latch, List<String> list, List<String> totalList) {
        this.latch = latch;
        this.list = list;
        this.totalList = totalList;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
            Thread.sleep(2000);
            System.out.println(list);
            totalList.addAll(list);
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
            throw new InterruptedException();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 将countDownLatch的递减放到finally中去执行，这样会确保主线程不会一直阻塞
             latch.countDown();// 减一
        }
    }
    
}
