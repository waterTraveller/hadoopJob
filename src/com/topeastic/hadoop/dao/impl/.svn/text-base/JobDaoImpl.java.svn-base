package com.topeastic.hadoop.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobID;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topeastic.hadoop.dao.JobDao;
import com.topeastic.hadoop.entity.LocalJob;
import com.topeastic.hadoop.thread.StartThread;
import com.topeastic.hadoop.utils.JobClientConn;

@Service("jobDao")
public class JobDaoImpl implements JobDao {

	private static final Logger logger = Logger.getLogger(JobDaoImpl.class);

	
	@Override
	public void startJob(String jobJarName,String jobClassName, String jobInputPath,
			String jobOutputPath,String id) throws Throwable {
		
		StartThread thread=new StartThread(jobJarName,jobClassName, jobInputPath,
				jobOutputPath,id);
		Thread t = new Thread(thread);
		t.start();
	}

	/*@Override
	public void startJob(String jarFile, String input, String output,LocalJob localJob)
			throws Throwable {
		String args[] = { jarFile, input, output };
		String mainClassName = "org.apache.hadoop.util.RunJar";
		Class<?> mainClass = Class.forName(mainClassName);
		Method main = mainClass.getMethod("main", new Class[] { Array
				.newInstance(String.class, 0).getClass() });
		try {
			main.invoke(null, new Object[] { args });
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}*/

	@Override
	public void reStart() {

	}

	@Override
	public JobStatus[] getAllJob() {
		try {
			logger.info("开始获取所有的Hadoop Job信息......");
			return JobClientConn.getConnection().getAllJobs();
		} catch (IOException e) {
			logger.error("获取Hadoop Job信息失败.....");
			e.printStackTrace();
		}
		logger.error("获取Hadoop Job信息失败.....但不是IO异常");
		return null;
	}

	@Override
	public JobID[] getJobId(JobClient jobClient, JobStatus[] jobStatus,
			String JobName) {
		List<JobID> jobids = new ArrayList<JobID>();
		for (int i = 0; i < jobStatus.length; i++) {
			try {
				RunningJob job = jobClient.getJob(jobStatus[i].getJobID());
				if (job.getJobName().trim().equals(JobName)) {
					jobids.add(job.getID());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (JobID[]) jobids.toArray(new JobID[jobids.size()]);
	}

	@Override
	public JobID[] getJobId(JobClient jobClient) {
		JobStatus[] jobs = null;
		List<JobID> jobids = new ArrayList<JobID>();
		try {
			// 获取正在运行的 没有失败的job
			jobs = jobClient.jobsToComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < jobs.length; i++) {
			jobids.add(jobs[i].getJobID());
		}
		return (JobID[]) jobids.toArray(new JobID[jobids.size()]);
	}

	@Override
	public JobID[] getFailedJob(JobClient jobClient) {
		JobStatus[] jobs = getAllJob();
		List<JobID> jobids = new ArrayList<JobID>();
		for (int i = 0; i < jobs.length; i++) {
			if (jobs[i].getRunState() == 3) {
				jobids.add(jobs[i].getJobID());
			}
		}
		return (JobID[]) jobids.toArray(new JobID[jobids.size()]);
	}

	@Override
	public RunningJob getJob(JobClient jobClient, JobID jobId) {
		try {
			return jobClient.getJob(jobId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public float[] getScheduleById(JobClient jobClient, JobID jobId) {
		RunningJob job = getJob(jobClient, jobId);
		float[] Schedule = new float[2];
		try {
			if (!job.isComplete()) {
				float mapSchedule = job.mapProgress();
				float reduceSchedule = job.reduceProgress();
				Schedule[0] = mapSchedule;
				Schedule[1] = reduceSchedule;
				return Schedule;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Schedule[0] = 1.0f;
		Schedule[1] = 1.0f;
		return Schedule;
	}

	
}
