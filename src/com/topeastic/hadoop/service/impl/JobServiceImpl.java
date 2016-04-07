package com.topeastic.hadoop.service.impl;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobID;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topeastic.hadoop.dao.JobDao;
import com.topeastic.hadoop.dao.LocalJobDao;
import com.topeastic.hadoop.entity.LocalJob;
import com.topeastic.hadoop.entity.User;
import com.topeastic.hadoop.service.JobService;

@Service("jobService")
public class JobServiceImpl implements JobService {
	
	static final Logger logger = Logger.getLogger(JobServiceImpl.class);
	
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private LocalJobDao localJobDao;

	public JobServiceImpl() {
		super();
	}

	/**
	 * JobStatus<br>
	 * 1.RUNNING<br>
	 * 2.SUCCESSED<br>
	 * 3.FAILED<br>
	 * 4.PREP<br>
	 * 5.KILLED
	 */
	@Override
	public JobStatus[] getAllJob() {
		return jobDao.getAllJob();
	}
	@Override
	public List<LocalJob> getLocalAllJob() {
		
		return localJobDao.showAll();
	}

	@Override
	public void startJob(String jobJarName,String jobClassName, String jobInputPath,
			String jobOutputPath,String id) throws Throwable {
		jobDao.startJob(jobJarName, jobClassName, jobInputPath, jobOutputPath,id);
	}
	
	@Override
	public void reStart() {
		
	}

	// 根据JobName 获取正在运行的JobID
	@Override
	public JobID[] getJobId(JobClient jobClient,JobStatus[] jobStatus,String JobName) {
		
		return jobDao.getJobId(jobClient,jobStatus, JobName);
	}

	// 获取运行的JobID
	@Override
	public JobID[] getJobId(JobClient jobClient) {
		return jobDao.getJobId(jobClient);
	}

	//
	@Override
	public JobID[] getFailedJob(JobClient jobClient) {
		return jobDao.getFailedJob(jobClient);
	}

	@Override
	public RunningJob getJob(JobClient jobClient,JobID jobId) {
		return jobDao.getJob(jobClient,jobId);
	}

	/**
	 * 根据JobID 获取job运行的进度 包括 map reduce<br>
	 * float 0.0~1.0
	 */
	@Override
	public float[] getScheduleById(JobClient jobClient,JobID jobId) {
		return jobDao.getScheduleById(jobClient,jobId);
	}

	@Override
	public void startJob() {
		logger.info("Start JOB.....");
		
	}

	@Override
	public boolean addLocalJob(LocalJob localJob) {
		logger.info("添加Job入库....");
		
		return localJobDao.insertJob(localJob);
	}

	@Override
	public List<LocalJob> getLocalAllJobByUser(User user) {
		return localJobDao.getLocalJobByUser(user);
	}

	@Override
	public List<LocalJob> getLocalRunningJob() {
		// TODO Auto-generated method stub
		return localJobDao.getLocalRunningJob();
	}

	@Override
	public List<LocalJob> getLocalRunningJob(User user) {
		// TODO Auto-generated method stub
		return localJobDao.getLocalRunningJob(user);
	}

	@Override
	public List<LocalJob> getLocalCompletedJob() {
		// TODO Auto-generated method stub
		return localJobDao.getLocalCompletedJob();
	}

	@Override
	public List<LocalJob> getLocalCompletedJob(User user) {
		// TODO Auto-generated method stub
		return localJobDao.getLocalCompletedJob(user);
	}

	@Override
	public void updateLocalRunningJob() {
		// TODO Auto-generated method stub
		localJobDao.updateLocalRunningJob();
	}

	@Override
	public void updateLocalRunningJob(String id, String info) {
		// TODO Auto-generated method stub
		localJobDao.updateLocalRunningJob(id,info);
	}

	@Override
	public LocalJob getLocalJobByJobId(String jobId) {
		// TODO Auto-generated method stub
		return localJobDao.getLocalJobByJobId(jobId);
	}

}
