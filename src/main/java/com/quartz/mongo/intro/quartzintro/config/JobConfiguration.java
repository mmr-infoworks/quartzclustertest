package com.quartz.mongo.intro.quartzintro.config;

import static org.quartz.TriggerBuilder.newTrigger;

import com.quartz.mongo.intro.quartzintro.scheduler.jobs.SampleJob2;
import com.quartz.mongo.intro.quartzintro.scheduler.jobs.SampleJob3;
import com.quartz.mongo.intro.quartzintro.scheduler.jobs.SampleJob4;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import java.util.Set;
import javax.annotation.PostConstruct;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.quartz.mongo.intro.quartzintro.constants.SchedulerConstants;
import com.quartz.mongo.intro.quartzintro.scheduler.jobs.SampleJob1;

/**
 * 
 * This will configure the job to run within quartz.
 * 
 * @author dinuka
 *
 */
@Configuration
public class JobConfiguration {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@PostConstruct
	private void initialize() throws Exception {
		try {
			Class[] jobClasses = {SampleJob1.class, SampleJob2.class, SampleJob3.class, SampleJob4.class};
			for (int i = 0; i < jobClasses.length; i++) {
				Class jobClass = jobClasses[i];
				schedulerFactoryBean.getScheduler().addJob(sampleJobDetail(jobClass), true, true);
				if (!schedulerFactoryBean.getScheduler().checkExists(new TriggerKey(
						"job-" + jobClass.getCanonicalName(), "group-"))) {
					schedulerFactoryBean.getScheduler().scheduleJob(sampleJobTrigger(jobClass));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * <p>
	 * The job is configured here where we provide the job class to be run on
	 * each invocation. We give the job a name and a value so that we can
	 * provide the trigger to it on our method {@link #sampleJobTrigger(Class)}
	 * </p>
	 * 
	 * @return an instance of {@link JobDetail}
	 */
	private static JobDetail sampleJobDetail(Class cls) {
		JobDetailImpl jobDetail = new JobDetailImpl();
		jobDetail.setKey(
				new JobKey(getJobKey(cls), getJobGroup(cls)));
		jobDetail.setJobClass(cls);
		jobDetail.setDurability(true);
		return jobDetail;
	}

	/**
	 * <p>
	 * This method will define the frequency with which we will be running the
	 * scheduled job which in this instance is every minute three seconds after
	 * the start up.
	 * </p>
	 * 
	 * @return an instance of {@link Trigger}
	 */
	private static Trigger sampleJobTrigger(Class cls) {
		return newTrigger().forJob(sampleJobDetail(cls))
				.withIdentity(getJobKey(cls) ,
						getJobGroup(cls))
				.withPriority(50).withSchedule(SimpleScheduleBuilder.repeatMinutelyForever())
				.startAt(Date.from(LocalDateTime.now().plusSeconds(3).atZone(ZoneId.systemDefault()).toInstant()))
				.build();
	}

	private static String getJobKey(Class cls) {
		return "job-" +cls.getSimpleName();
	}

	private static String getJobGroup(Class cls) {
		return "group-" +cls.getSimpleName();
	}

}