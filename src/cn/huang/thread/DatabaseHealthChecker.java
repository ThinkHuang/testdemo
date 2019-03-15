/**
 * 
 */
package thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author dell
 *
 */
public class DatabaseHealthChecker extends BaseHealthChecker {

	/**
	 * @param _latch
	 * @param _serviceName
	 */
	public DatabaseHealthChecker(CountDownLatch _latch, String _serviceName) {
		super(_latch, _serviceName);
	}

	@Override
	public void executeService() {
		System.out.println("Checking: " + this.getServiceName());
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(this.getServiceName() + " is UP");
	}

    @Override
    public void run()
    {
        
    }

}
