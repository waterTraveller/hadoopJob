package com.topeastic.hadoop.thread;

import org.apache.log4j.Logger;

import com.topeastic.hadoop.tool.HadoopJobShell;
import com.topeastic.hadoop.utils.Config;

public class StartThread implements Runnable {

	static final Logger logger = Logger.getLogger(StartThread.class);

	private String jobId;
	private String jobJarName;
	private String jobClassName;
	private String jobInputPath;
	private String jobOutputPath;
	

	public StartThread() {
	}

	public StartThread(String jobJarName,String jobClassName, String jobInputPath,
			String jobOutputPath,String jobId) {
		super();
		this.jobJarName = jobJarName;
		this.jobClassName=jobClassName;
		this.jobInputPath = jobInputPath;
		this.jobOutputPath = jobOutputPath;
		this.jobId=jobId;
	}

	@Override
	public void run() {
		logger.info("线程启动...");
		HadoopJobShell shell = new HadoopJobShell(Config.ip, Config.user,
				Config.passwd, "utf8",jobId);
		String cmd = "hadoop jar " + jobJarName + " " + jobClassName + " " + jobInputPath
				+ " " + jobOutputPath;
		logger.info(cmd);
		shell.exec("source /etc/profile;" + cmd);
	}
}
