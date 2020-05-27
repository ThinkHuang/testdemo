package concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 分布式计算测试
 *
 */
public class DistributeCalculateForTest {
    
    // 默认线程数为4
    
    static final List<String> totalList = new CopyOnWriteArrayList<>();
    
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(4);
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i"));
        int size = list.size();
        int threadSize = 4;
        int partitionSize = size / threadSize;
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < threadSize; i++) {
            List<String> segmentUserIds = list.subList(i * partitionSize, partitionSize * (i + 1));
            if (i == threadSize - 1) {
                segmentUserIds = list.subList(i * partitionSize, size);
            }
            
            Worker worker = new Worker(latch, segmentUserIds, totalList);
            executorService.submit(worker);
        }
        
        try {
            latch.await();
            System.out.println("主线程结束");
            System.out.println(totalList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
