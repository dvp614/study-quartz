package org.zerock.myapp.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// Quartz Scheduler에 등록시킬 작업(Task)는 반드시
// Quartz 가 제공하는 Job이란 인터페이스를 구현해야 합니다.
public class SimpleTask implements Job{
	
	@Override
	// 일정에 따라 실행될 로직을 이 메소드 안에서 구현
	public void execute(JobExecutionContext arg0) 
			throws JobExecutionException {
		log.trace("exceute({}) invoked.", arg0);
		
		try {
			log.info(">>> I'm a SimpleTask.");
		}catch(Exception e) {
			throw new JobExecutionException(e);
		} // try-catch
		
	}	// POJO
	
} // end class
