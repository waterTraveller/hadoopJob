package com.topeastic.hadoop.job;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.log4j.Logger;

import com.topeastic.hadoop.dao.impl.LocalJobDaoImpl;
import com.topeastic.hadoop.entity.LocalJob;
import com.topeastic.hadoop.utils.Config;
import com.topeastic.hadoop.utils.FtpUtils;
import com.topeastic.hadoop.utils.JobClientConn;

public class UpdateJobTask extends TimerTask {

	private static final Logger logger = Logger.getLogger(UpdateJobTask.class);
	private String HadoopId;

	public UpdateJobTask() {
	}

	public UpdateJobTask(String HadoopId) {
		this.HadoopId = HadoopId;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		// 首先获取所有的RunningJob
		// 根据HadoopId 获取本地和集群的Job
		LocalJobDaoImpl dao = new LocalJobDaoImpl();
		LocalJob localJob = dao.getLocalJobByHadoopId(HadoopId);

		RunningJob hadoopJob = null;
		JobClient jobClient = JobClientConn.getConnection();
		try {
			
			hadoopJob = jobClient.getJob(HadoopId);
			
			// job 状态 发生了变化 就更新本地job状态
			
			if (!localJob.getJobState().equals("" + hadoopJob.getJobState())) {
				logger.info("Hadoop Job**" + HadoopId + "状态发生变化,更新本地Job状态...");
				dao.updateLocalRunningJobState(localJob.getId(),
						hadoopJob.getJobState());
			}
			if (localJob.getJobState().equals("2")) {
				dao.updateLocalRunningJobEndTime(localJob.getId(),
						System.currentTimeMillis());
				this.cancel();
				// 删除运行的jar文件
				String[] name = localJob.getJobJarName().split("/");
				FtpUtils.deleteFile(Config.ip, 21, Config.user, Config.passwd,
						Config.savePath, name[name.length - 1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
