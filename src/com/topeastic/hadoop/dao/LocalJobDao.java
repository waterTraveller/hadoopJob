package com.topeastic.hadoop.dao;

import java.util.List;

import com.topeastic.hadoop.entity.LocalJob;
import com.topeastic.hadoop.entity.User;

/**
 * 用户对数据库的Job进行处理的接口
 * 
 * @author root
 * 
 */
public interface LocalJobDao {

	/**
	 * 查询所有的LocalJob
	 */
	List<LocalJob> showAll();

	/**
	 * 开始任务
	 */
	void startJob();

	/**
	 * 根据jobId重启任务
	 * 
	 * @param jobId
	 */
	void restartJob(String jobId);

	boolean insertJob(LocalJob localJob);

	List<LocalJob> getLocalJobByUser(User user);

	/**
	 * 获取正在运行的Job
	 * 
	 * @return
	 */
	List<LocalJob> getLocalRunningJob();

	/**
	 * 根据用户获取正在运行的Job
	 * 
	 * @param user
	 * @return
	 */
	List<LocalJob> getLocalRunningJob(User user);

	/**
	 * 获取完成的Job
	 * 
	 * @return
	 */
	List<LocalJob> getLocalCompletedJob();

	/**
	 * 根据用户获取完成的Job
	 * 
	 * @param user
	 * @return
	 */
	List<LocalJob> getLocalCompletedJob(User user);
	
	/**
	 * 更新所有的本地Job
	 */
	void updateLocalRunningJob();

	/**
	 * 将HadoopJobId更新至localJob
	 * @param id
	 * @param info
	 */
	void updateLocalRunningJob(String id, String info);

	/**
	 * 更新localJob状态
	 * @param id
	 * @param state
	 */
	void updateLocalRunningJobState(String id, int state);
	
	LocalJob getLocalJobByHadoopId(String HadoopId);

	void updateLocalRunningJobEndTime(String JobId, long currentTimeMillis);

	LocalJob getLocalJobByJobId(String jobId);
	
}