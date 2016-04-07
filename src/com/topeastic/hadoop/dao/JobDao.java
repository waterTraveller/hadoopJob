package com.topeastic.hadoop.dao;

import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobID;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapred.RunningJob;

import com.topeastic.hadoop.entity.Job;
import com.topeastic.hadoop.entity.LocalJob;
import com.topeastic.hadoop.entity.User;

public interface JobDao {

	void startJob(String jobJarName,String jobClassName, String jobInputPath,
			String jobOutputPath,String id)
			throws Throwable;

	void reStart();

	/**
	 * 获取所有的HadoopJob
	 * @param jobClient
	 * @return
	 */
	JobStatus[] getAllJob();

	/**
	 * 根据JobName来获取该Job的JobId
	 * @param jobClient
	 * @param JobName
	 * @return
	 */
	JobID[] getJobId(JobClient jobClient,JobStatus[] jobStatus,String JobName);
	
	
	
	/**
	 * 获取所有的JobId
	 * @param jobClient
	 * @return
	 */
	JobID[] getJobId(JobClient jobClient);

	/**
	 * 获取所有失败的JobId
	 * @param jobClient
	 * @return
	 */
	JobID[] getFailedJob(JobClient jobClient);

	/**
	 * 获取RunningJob
	 * @param jobClient
	 * @param jobId
	 * @return
	 */
	RunningJob getJob(JobClient jobClient,JobID jobId);

	/**
	 * 根据JobID 获取job运行的进度 包括 map reduce
	 * @param jobClient
	 * @param jobId
	 * @return
	 */
	float[] getScheduleById(JobClient jobClient,JobID jobId);
	
	
	
	

}
