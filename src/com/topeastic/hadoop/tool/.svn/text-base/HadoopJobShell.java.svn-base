package com.topeastic.hadoop.tool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;

import org.apache.log4j.Logger;

import com.topeastic.hadoop.dao.impl.LocalJobDaoImpl;
import com.topeastic.hadoop.job.UpdateJobTask;

public class HadoopJobShell extends ShellTool {

	private static final Logger logger = Logger.getLogger(HadoopJobShell.class);

	private String jobId;

	public HadoopJobShell(String ipAddr, String userName, String password,
			String charset,String jobId) {
		super(ipAddr, userName, password, charset);
		this.jobId=jobId;
	}

	public void processStdout(InputStream in, String charset) {

		BufferedReader bReader = null;
		String line;
		Timer timer=null;
		LocalJobDaoImpl dao=new LocalJobDaoImpl();
		try {
			bReader = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(in), charset));
			while ((line = bReader.readLine()) != null) {
				logger.info(line);
				String[] strs = line.split(" ");
				for (int i = 0; i < strs.length; i++) {
					if (strs[i].equals("job:")) {
						logger.info("获取hadoop Job的任务Id：" + strs[i + 1]);
						logger.info("添加job 入库:本地localJobId："+jobId+" Hadoop Id:"+strs[i + 1]);
						dao.updateLocalRunningJob(jobId,strs[i + 1]);
						timer=new Timer();
						//设定 更新状态的任务
						timer.schedule(new UpdateJobTask(strs[i + 1]), 500, 1*1000);
					}
				}
			}
			//任务结束 关闭监控任务
			
			bReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
