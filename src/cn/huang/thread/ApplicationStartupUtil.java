/**
 * 
 */
package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author dell
 * 主线程的启动类
 */
public class ApplicationStartupUtil {
	
	private static List<BaseHealthChecker> list;
	
	private static CountDownLatch _latch;
	
//	private final ApplicationStartupUtil instance = new ApplicationStartupUtil();
//	
//	public ApplicationStartupUtil getInstance(){
//		return instance;
//	}
	
	
	public static boolean checkIsExecute(){
		
		_latch = new CountDownLatch(2);//如果CountDownLatch和线程数不匹配将会导致永远处于等待状态
		list = new ArrayList<BaseHealthChecker>(2);
		list.add(new DatabaseHealthChecker(_latch, "DatabaseHealthChecker"));
		list.add(new NetworkHealthChecker(_latch, "NetworkHealthChecker"));
		
		ExecutorService executor = Executors.newFixedThreadPool(list.size());
		
		for (final BaseHealthChecker v : list) {
			executor.execute(v);
		}
		
		try {
			_latch.await();
			executor.shutdownNow();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		for (final BaseHealthChecker v : list) {
			if(!v.isServiceUp()){
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		boolean result = false;
        try {
            result = ApplicationStartupUtil.checkIsExecute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
	}
}
