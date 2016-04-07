package com.topeastic.hadoop.service;

import java.util.List;

import org.apache.hadoop.mapred.JobID;

import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapred.RunningJob;
import org.springframework.stereotype.Service;

import com.topeastic.hadoop.entity.LocalJob;
import com.topeastic.hadoop.entity.User;

public interface JobService {
	
	void startJob();

	void startJob(String jobJarName,String jobClassName, String jobInputPath,
			String jobOutputPath,String jobId)throws Throwable;
	
	void reStart();

	/**
	 * 获取所有提交完成的Job
	 * @param jobClient
	 * @return
	 */
	JobStatus[] getAllJob();
	
	List<LocalJob> getLocalAllJob();
	/**
	 * 根据用户信息查询用户的jobs
	 * @return
	 */
	List<LocalJob> getLocalAllJobByUser(User user);
	
	/**
	 * 获取正在运行的Job
	 * @return
	 */
	List<LocalJob> getLocalRunningJob();
	
	/**
	 * 根据用户获取正在运行的Job
	 * @param user
	 * @return
	 */
	List<LocalJob> getLocalRunningJob(User user);
	
	/**
	 * 获取完成的Job
	 * @return
	 */
	List<LocalJob> getLocalCompletedJob();
	
	/**
	 * 根据用户获取完成的Job
	 * @param user
	 * @return
	 */
	List<LocalJob> getLocalCompletedJob(User user);
	
	boolean addLocalJob(LocalJob localJob);

	/**
	 * 根据JobName 获取正在运行的JobID
	 * @param jobClient
	 * @param jobStatus
	 * @param JobName
	 * @return
	 */
	JobID[] getJobId(JobClient jobClient,JobStatus[] jobStatus,String JobName);

	/**
	 * 获取正在运行的所有的JobID
	 * @param jobClient
	 * @return
	 */
	JobID[] getJobId(JobClient jobClient);

	/**
	 * 获取失败的JobID
	 * @param jobClient
	 * @return
	 */
	JobID[] getFailedJob(JobClient jobClient);

	/**
	 * 根据JobID获取runningjob
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

	/**
	 * 根据JobId查询
	 */
	
	/**
	 * 更新所有的本地Job
	 */
	void updateLocalRunningJob();

	/**
	 * 将HadoopJobId插入localJob
	 * @param id
	 * @param info
	 */
	void updateLocalRunningJob(String id, String info);

	LocalJob getLocalJobByJobId(String jobId);
	
}
