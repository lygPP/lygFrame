package com.lyg.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.expression.ParseException;

/**
 * @Title:Quartz管理类 
 *  
 * @Description:  
 * @author lyg  
 * @date 2017-09-08 
 * @version 1.0 
 * 
 */
public class QuartzManager {
	private static SchedulerFactory sf = new StdSchedulerFactory();  
	   private static String JOB_GROUP_NAME = "group1";  
	   private static String TRIGGER_GROUP_NAME = "trigger1";  
	    
	     
	   /**
	    *  添加一个定时任务，使用默认的任务组名，触发器名，触发器组名 
	    * @param jobName 任务名 
	    * @param job     任务 
	    * @param time    时间设置，参考quartz说明文档 
	    * @throws SchedulerException 
	    * @throws ParseException 
	    */  
	   public static void addJob(String jobName,Job job,String time)   
	                               throws SchedulerException, ParseException{  
	       Scheduler sched = sf.getScheduler();
	       
	       JobDetail jobDetail = JobBuilder.newJob(job.getClass())
	                .withIdentity(jobName, QuartzManager.JOB_GROUP_NAME)
	                .build();
			
		   Trigger trigger = TriggerBuilder.newTrigger()
	                .withIdentity(jobName,TRIGGER_GROUP_NAME)
	                .startNow()
	                .withSchedule(
	                    CronScheduleBuilder.cronSchedule(time)
	                ).build();
		   
		   sched.scheduleJob(jobDetail, trigger);
	       //启动  
	       if(!sched.isShutdown())  
	          sched.start();  
	   }  
	     
	   /** *//** 
	    * 添加一个定时任务 
	    * @param jobName 任务名 
	    * @param jobGroupName 任务组名 
	    * @param triggerName  触发器名 
	    * @param triggerGroupName 触发器组名 
	    * @param job     任务 
	    * @param time    时间设置，参考quartz说明文档 
	    * @throws SchedulerException 
	    * @throws ParseException 
	    */  
	   public static void addJob(String jobName,String jobGroupName,  
	                             String triggerName,String triggerGroupName,  
	                             Job job,String time)   
	                               throws SchedulerException, ParseException{  
	       Scheduler sched = sf.getScheduler();  

	       JobDetail jobDetail = JobBuilder.newJob(job.getClass())
	                .withIdentity(jobName, jobGroupName)
	                .build();
			
		   Trigger trigger = TriggerBuilder.newTrigger()
	                .withIdentity(triggerName,triggerGroupName)
	                .startNow()
	                .withSchedule(
	                    CronScheduleBuilder.cronSchedule(time)
	                ).build();
		   
		   sched.scheduleJob(jobDetail, trigger);
	       if(!sched.isShutdown())  
	          sched.start();  
	   }  
	     
	   /**
	    * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名) 
	    * @param jobName 
	    * @param time 
	    * @throws SchedulerException 
	    * @throws ParseException 
	    */  
	   public static void modifyJobTime(String jobName,String time)   
	                                  throws SchedulerException, ParseException{  
	       Scheduler sched = sf.getScheduler();  
	       Trigger trigger =  sched.getTrigger(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));
	       if(trigger != null){
	    	   Trigger ct = TriggerBuilder.newTrigger()
		                .withIdentity(jobName, TRIGGER_GROUP_NAME)
		                .startNow()
		                .withSchedule(
		                    CronScheduleBuilder.cronSchedule(time)
		                ).build();
	           sched.rescheduleJob(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME), ct);
	       }  
	   }
	     
	   /**
	    * 移除一个任务(使用默认的任务组名，触发器名，触发器组名) 
	    * @param jobName 
	    * @throws SchedulerException 
	    */  
	   public static void removeJob(String jobName)   
	                               throws SchedulerException{  
	       Scheduler sched = sf.getScheduler();  
	       sched.pauseTrigger(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));//停止触发器  
	       sched.unscheduleJob(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));//移除触发器  
	       sched.deleteJob(JobKey.jobKey(jobName,JOB_GROUP_NAME));//删除任务  
	   }  
	     
	   /** 
	    * 移除一个任务 
	    * @param jobName 
	    * @param jobGroupName 
	    * @param triggerName 
	    * @param triggerGroupName 
	    * @throws SchedulerException 
	    */  
	   public static void removeJob(String jobName,String jobGroupName,  
	                                String triggerName,String triggerGroupName)   
	                               throws SchedulerException{  
	       Scheduler sched = sf.getScheduler();  
	       sched.pauseTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));//停止触发器  
	       sched.unscheduleJob(TriggerKey.triggerKey(triggerName, triggerGroupName));//移除触发器  
	       sched.deleteJob(JobKey.jobKey(jobName,jobGroupName));//删除任务 
	   }
}
