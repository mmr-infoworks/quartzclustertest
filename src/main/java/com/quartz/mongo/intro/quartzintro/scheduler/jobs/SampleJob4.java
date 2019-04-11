package com.quartz.mongo.intro.quartzintro.scheduler.jobs;

import com.quartz.mongo.intro.quartzintro.config.JobConfiguration;
import com.quartz.mongo.intro.quartzintro.config.QuartzConfiguration;
import com.quartz.mongo.intro.quartzintro.constants.Node;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 
 * This is the job class that will be triggered based on the job configuration
 * defined in {@link JobConfiguration}
 * 
 * @author dinuka
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SampleJob4 extends QuartzJobBean {

	private static Logger log = LoggerFactory.getLogger(SampleJob4.class);

	private ApplicationContext applicationContext;

	/**
	 * This method is called by Spring since we set the
	 * {@link SchedulerFactoryBean#setApplicationContextSchedulerContextKey(String)}
	 * in {@link QuartzConfiguration}
	 * 
	 * @param applicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * This is the method that will be executed each time the trigger is fired.
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("This is the sample job 4, executed by {}", Node.getName());

	}
}