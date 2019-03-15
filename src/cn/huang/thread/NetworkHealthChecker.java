/**
 * 
 */
package com.huang.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @author dell
 *
 */
public class NetworkHealthChecker extends BaseHealthChecker {

	/**
	 * @param _latch
	 * @param _serviceName
	 */
	public NetworkHealthChecker(CountDownLatch _latch, String _serviceName) {
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

}
