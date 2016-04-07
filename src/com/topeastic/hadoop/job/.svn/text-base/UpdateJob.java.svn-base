package com.topeastic.hadoop.job;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 程序启动时 就会不停的更新 HadoopJob任务的状态
 * @author root
 *
 */
public class UpdateJob implements ServletContextListener{
	
	private Timer timer=null;
	private static Logger logger =  Logger.getLogger(UpdateJob.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		timer=new Timer(true);
		timer.schedule(new UpdateJobTask(), 10*1000, 10*1000);
	}

}
