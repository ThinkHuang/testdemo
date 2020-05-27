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
            latch.countDown();// 减一
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
