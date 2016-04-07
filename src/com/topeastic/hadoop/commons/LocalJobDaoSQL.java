package com.topeastic.hadoop.commons;

/**
 * LocalJobDao类的SQL语句
 * @author root
 *
 */
public class LocalJobDaoSQL {

	/**
	 * 查询所有的job的sql
	 */
	public static final String showAll="select * from hadoop_job";

	/**
	 * 根据LocalJob 插入数据库
	 */
	public static final String insert="insert into hadoop_job values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	/**
	 * 根据用户信息查询用户相关的Job
	 */
	public static final String selectByUserId="select * from hadoop_job where jobuserid = ?";
	
	/**
	 * 查询所有运行中的Job
	 */
	public static final String selectRunningJob="select * from hadoop_job where jobstate = 1 or JobState= 4 ";
	
	/**
	 * 查询用户的运行中的Job
	 */
	public static final String selectRunningJobByUser="select * from hadoop_job where (jobstate = 1 or JobState= 4) and jobuserid = ?";
	
	/**
	 * 查询所有完成的Job
	 */
	public static final String selectCompletedJob="select * from hadoop_job where jobstate = 2";
	
	/**
	 * 根据用户查询所有完成的Job
	 */
	public static final String selectCompletedJobByUser="select * from hadoop_job where jobstate= 2 and jobuserid = ?";

	/**
	 * 插入Local数据库
	 */
	public static final String addLocal="";
	
	/**
	 * 更新hadoopId
	 */
	public static final String insertHadoopId="update hadoop_job set JobHadoopId = ? where Id = ?";
	
	/**
	 * 更新JobState
	 */
	public static final String updateJobState="update hadoop_job set JobState = ? where Id = ?";
	
	/**
	 * 根据HadoopId获取本地Job
	 */
	public static final String getLocalJobByHadoopId="select * from hadoop_job where JobHadoopId = ?";
	
	/**
	 * 更新本地任务的结束时间
	 */
	public static final String updateJobEndTime="update hadoop_job set JobEndTime = ? where Id = ?";
	
	/**
	 * 根据JobId 获取本地Job
	 */
	public static final String getLocalJobByJobId="select * from hadoop_job where JobId = ?";
}
