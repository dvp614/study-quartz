package org.zerock.myapp;

import java.util.Arrays;
import java.util.Objects;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.zerock.myapp.task.SimpleTask;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class QuartzExample {
    public static void main( String[] args ) throws SchedulerException{
        log.trace("main({}) invoked.", Arrays.toString(args));
        
        // Step1. Quartz의 Job Scheduler 인스턴스 생성
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        log.info("\tStep1. scheduler : {}, type : {}", scheduler, scheduler.getClass().getName());
        
        // Step2. Step1 에서 생성한 Scheduler 에 미리 선언한 Job(Task) 클래스를 등록
        Objects.requireNonNull(scheduler);
        
        JobDetail simpleJob =
        	JobBuilder.newJob(SimpleTask.class)
        		// 작업의 이름으로 등록
//        		.withIdentity("SimpleTask")
        		// 작업의 이름과 작업의 소속그룹명을 등록
        		.withIdentity("SimpleTask", "GROUP1")
        		.build();
        log.info("\tStep2. simpleJob : {}", simpleJob);
        
        // Step3. Step2에서 생성한 JobDetail에 대한 일정 생성
        Trigger simpleJobTrigger = 
        	TriggerBuilder.newTrigger()
        		.withIdentity("simpleJobTrigger", "GROUP1")
        		.startNow()
        		.withSchedule(
        				SimpleScheduleBuilder.simpleSchedule()
//        				.withIntervalInSeconds(1)
        				.withIntervalInMilliseconds(1000L * 3)
        				.repeatForever()
        		)
        		.build();
        // Step4. Step2에서 생성한 JobDetail과 Step3에서 생성한 Trigger(일정)으로,
        //        Quartz Scheduler에 최종등록
        scheduler.scheduleJob(simpleJob, simpleJobTrigger);
        
        // Step3. Step1. Quartz 의 Job Scheduler를 구동.
        scheduler.start();
    } // main
} // end clas
