package cn.huang.clock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 该类主要实现Java代码的定时任务
 * @author dell
 *
 */
public class ClockTaskDemo {
	
	/**
	 * 第一种，利用thread来开启一个线程，进行不断循环，知道被唤醒
	 * 缺点：太消耗资源，且容易发生内存泄漏，不提倡使用,另外不够精确，当服务器的服务的越久，误差会越来越大
	 */
	@Test
	public void task1(){
		
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				/*
				 * the task to run
				 */
				while(true){
					try {
						Thread.sleep(3*1000);//睡眠3秒
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		});
	
		thread.start();
	}
	
	/**
	 * 利用Timer实现定时
	 * 缺点，不利于控制，针对广发小程序不太适合，如果当其中一个客户的邮件附件处理过于复杂，耗时过多，那么将会打乱定时任务的执行--来自于Timer API
	 * 这是一个单线程任务处理器
	 * 相比较于第一种，添加了更多的灵活性，可以设置任务执行的间隔
	 */
	@Test
	public void task2(){
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				/*
				 * the task to run
				 */
			}
		};
		
		Timer timer = new Timer();
		// schedules the task to be ran in an interval  
		long internalPeriod = 1000;//任务执行的间隔
		long delay = 0;//延迟
		timer.scheduleAtFixedRate(task, delay, internalPeriod);
	}
	
	
	/**
	 * 利用ScheduledExecutorService实现定时任务
	 */
	@Test
	public void task3(){
		
		Runnable runnable = new Runnable(){

			@Override
			public void run() {
				/*
				 * the task to run
				 */
			}
			
		};
		
		ScheduledExecutorService service = 
						(ScheduledExecutorService) Executors.newSingleThreadExecutor();
		//第二个参数为首次执行的延迟时间，第三个参数为定时执行的间隔，第四个参数是时间单位
		service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
	}
	
	
	/**
	 * 通过quartz来完成定时任务
	 * 依赖jar包：log4j.jar quartz.jar slf4j-api-1.6.1.jar slf4j-log4j.jar
	 * @throws SchedulerException 
	 */
	@Test
	public void task4() throws SchedulerException{
		/*
		 * 首先取得一个scheduler的引用
		 */
		SchedulerFactory sFactory = new StdSchedulerFactory();
		Scheduler scheduler = sFactory.getScheduler();
		
		/*
		 * 新建job1，该job1来自于继承的Job类
		 */
		JobDetail job1 = JobBuilder.newJob(myJob.class).withIdentity("job1", "group1").build();
		/*
		 * 新建定时触发器，并通过触发器设置定时执行的时间以及要执行那个定时任务，所以定时任务主要是定时触发器触发的，有Job完成具体的任务
		 */
		CronTrigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").
				withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?")).build();
		
		Date dt = scheduler.scheduleJob(trigger1);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(job1.getKey() + "已被安排执行于：" + df.format(dt) + "并且以如下规则重复执行：" + trigger1.getCronExpression());
		
		/*
		 * 创建job2
		 */
		JobDetail job2 = JobBuilder.newJob(myJob.class).withIdentity("job2", "group2").build();
		
		CronTrigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2").
				withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?")).build();
		
		dt = scheduler.scheduleJob(trigger2);
		
		System.out.println(job2.getKey() + "已被安排执行于：" + df.format(dt) + "并且以如下规则重复执行：" + trigger2.getCronExpression());
		
		scheduler.start();
		
		try {
			Thread.sleep(60 * 1000);
		} catch (Exception e) {
			scheduler.shutdown(true);
		}
				
	}
	
	
	
	
	class myJob implements Job{

		@Override
		public void execute(JobExecutionContext arg0)
				throws JobExecutionException {
			
		}
		
	}
}
