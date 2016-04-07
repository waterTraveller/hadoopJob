package com.topeastic.hadoop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.mapred.JobContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.topeastic.hadoop.commons.LocalJobDaoSQL;
import com.topeastic.hadoop.dao.LocalJobDao;
import com.topeastic.hadoop.entity.LocalJob;
import com.topeastic.hadoop.entity.User;
import com.topeastic.hadoop.utils.JdbcUtils;
import com.topeastic.hadoop.utils.StringUtils;

@Service("localJobDao")
public class LocalJobDaoImpl implements LocalJobDao {

	static final Logger logger = Logger.getLogger(LocalJobDaoImpl.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobContext context = null;
		context.getConfiguration();
	}

	@Override
	public List<LocalJob> showAll() {
		LocalJob local;
		List<LocalJob> localJobs = new ArrayList<LocalJob>();
		try {
			PreparedStatement ps = JdbcUtils.getConnection().prepareStatement(
					LocalJobDaoSQL.showAll);
			ResultSet rs = ps.executeQuery();
			localJobs = saveJob(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.commit();
		}
		return localJobs;
	}

	@Override
	public void startJob() {

	}

	@Override
	public void restartJob(String jobId) {

	}

	@Override
	public boolean insertJob(LocalJob localJob) {
		logger.info("localJob 导入数据库....");
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			logger.info("入库前查看localJob:" + localJob.toString());
			ps = conn.prepareStatement(LocalJobDaoSQL.insert);
			if (StringUtils.strIsNotNull(localJob.getId())) {
				ps.setString(1, localJob.getId());
			}
			if (StringUtils.strIsNotNull(localJob.getJobUserId())) {
				ps.setString(2, localJob.getJobUserId());
			} else {
				ps.setString(2, null);
			}
			if (StringUtils.strIsNotNull(localJob.getJobUserName())) {
				ps.setString(3, localJob.getJobUserName());
			} else {
				ps.setString(3, null);
			}
			if (StringUtils.strIsNotNull(localJob.getJobId())) {
				ps.setString(4, localJob.getJobId());
			} else {
				ps.setString(4, null);
			}
			if (StringUtils.strIsNotNull(localJob.getJobName())) {
				ps.setString(5, localJob.getJobName());
			} else {
				ps.setString(5, null);
			}
			if (StringUtils.strIsNotNull(localJob.getJobJarName())) {
				ps.setString(6, localJob.getJobJarName());
			} else {
				ps.setString(6, null);
			}
			if (StringUtils.strIsNotNull(localJob.getJobClassName())) {
				ps.setString(7, localJob.getJobClassName());
			} else {
				ps.setString(7, null);
			}
			if (StringUtils.strIsNotNull(localJob.getJobInputPath())) {
				ps.setString(8, localJob.getJobInputPath());
			} else {
				ps.setString(8, null);
			}
			if (StringUtils.strIsNotNull(localJob.getJobOutputPath())) {
				ps.setString(9, localJob.getJobOutputPath());
			} else {
				ps.setString(9, null);
			}
			if (StringUtils.strIsNotNull(localJob.getJobState())) {
				ps.setString(10, localJob.getJobState());
			} else {
				ps.setString(10, null);
			}
			if (StringUtils.strIsNotNull(localJob.getJobStartTime())) {
				ps.setString(11, localJob.getJobStartTime());
			} else {
				ps.setString(11, null);
			}
			if (StringUtils.strIsNotNull(localJob.getJobEndTime())) {
				ps.setString(12, localJob.getJobEndTime());
			} else {
				ps.setString(12, null);
			}
			if (StringUtils.strIsNotNull(localJob.getJobHadoopId())) {
				ps.setString(13, localJob.getJobHadoopId());
			} else {
				ps.setString(13, null);
			}

			logger.info("返回结果为：" + ps.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JdbcUtils.commit();
		}
		logger.info("添加入库成功....");
		return true;

	}

	@Override
	public List<LocalJob> getLocalJobByUser(User user) {
		PreparedStatement ps;
		ResultSet rs;
		LocalJob local;
		List<LocalJob> localJobs = new ArrayList<LocalJob>();
		try {
			ps = JdbcUtils.getConnection().prepareStatement(
					LocalJobDaoSQL.selectByUserId);
			ps.setString(1, user.getUserId());
			rs = ps.executeQuery();
			localJobs = saveJob(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.commit();
		}
		return localJobs;
	}

	@Override
	public List<LocalJob> getLocalRunningJob() {
		PreparedStatement ps;
		ResultSet rs;
		LocalJob local;
		List<LocalJob> localJobs = new ArrayList<LocalJob>();
		try {
			ps = JdbcUtils.getConnection().prepareStatement(
					LocalJobDaoSQL.selectRunningJob);
			rs = ps.executeQuery();
			localJobs = saveJob(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.commit();
		}
		return localJobs;
	}

	@Override
	public List<LocalJob> getLocalRunningJob(User user) {
		PreparedStatement ps;
		ResultSet rs;
		LocalJob local;
		List<LocalJob> localJobs = new ArrayList<LocalJob>();
		try {
			ps = JdbcUtils.getConnection().prepareStatement(
					LocalJobDaoSQL.selectRunningJobByUser);
			ps.setString(1, user.getUserId());
			rs = ps.executeQuery();
			localJobs = saveJob(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.commit();
		}
		return localJobs;
	}

	@Override
	public List<LocalJob> getLocalCompletedJob() {
		PreparedStatement ps;
		ResultSet rs;
		LocalJob local;
		List<LocalJob> localJobs = new ArrayList<LocalJob>();
		try {
			ps = JdbcUtils.getConnection().prepareStatement(
					LocalJobDaoSQL.selectCompletedJob);
			rs = ps.executeQuery();
			localJobs = saveJob(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.commit();
		}
		return localJobs;
	}

	@Override
	public List<LocalJob> getLocalCompletedJob(User user) {
		PreparedStatement ps;
		ResultSet rs;
		LocalJob local;
		List<LocalJob> localJobs = new ArrayList<LocalJob>();
		try {
			ps = JdbcUtils.getConnection().prepareStatement(
					LocalJobDaoSQL.selectCompletedJobByUser);
			ps.setString(1, user.getUserId());
			rs = ps.executeQuery();
			localJobs = saveJob(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.commit();
		}
		return localJobs;
	}

	public List<LocalJob> saveJob(ResultSet rs) throws SQLException {
		LocalJob local;
		List<LocalJob> localJobs = new ArrayList<LocalJob>();
		while (rs.next()) {
			local = new LocalJob();
			local.setId(rs.getString("Id"));
			local.setJobUserId(rs.getString("JobUserId"));
			local.setJobUserName(rs.getString("JobUserName"));
			local.setJobId(rs.getString("JobID"));
			local.setJobName(rs.getString("JobName"));
			local.setJobJarName(rs.getString("JobJarName"));
			local.setJobClassName(rs.getString("JobClassName"));
			local.setJobInputPath(rs.getString("JobInputPath"));
			local.setJobOutputPath(rs.getString("JobOutputPath"));
			local.setJobState(rs.getString("JobState"));
			local.setJobStartTime(new Date(Long.valueOf(rs
					.getString("JobStartTime"))).toLocaleString());
			local.setJobEndTime(rs.getString("JobEndTime") == null ? null
					: (new Date(Long.valueOf(rs.getString("JobEndTime")))
							.toLocaleString()));
			local.setJobHadoopId(rs.getString("JobHadoopId"));
			localJobs.add(local);
		}
		return localJobs;
	}

	@Override
	public void updateLocalRunningJob() {

	}

	@Override
	public void updateLocalRunningJob(String id, String info) {
		logger.info("更新HadoopId 至localJob");
		try {
			PreparedStatement ps=JdbcUtils.getConnection().prepareStatement(LocalJobDaoSQL.insertHadoopId);
			ps.setString(1, info);
			ps.setString(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.commit();
			logger.info("更新成功！！...");
		}
		
	}

	@Override
	public void updateLocalRunningJobState(String id, int state) {
		try{
			PreparedStatement ps=JdbcUtils.getConnection().prepareStatement(LocalJobDaoSQL.updateJobState);
			ps.setString(1, ""+state);
			ps.setString(2, id);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtils.commit();
			logger.info("任务:"+id+"更新成功！！...");
		}
	}

	@Override
	public LocalJob getLocalJobByHadoopId(String HadoopId) {
		PreparedStatement ps;
		ResultSet rs;
		List<LocalJob> jobs = null;
		try {
			ps=JdbcUtils.getConnection().prepareStatement(LocalJobDaoSQL.getLocalJobByHadoopId);
			ps.setString(1, HadoopId);
			rs=ps.executeQuery();
			jobs=saveJob(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.commit();
		}
		return jobs.get(0);
	}

	@Override
	public void updateLocalRunningJobEndTime(String JobId,long currentTimeMillis) {
		PreparedStatement ps;
		try {
			ps=JdbcUtils.getConnection().prepareStatement(LocalJobDaoSQL.updateJobEndTime);
			ps.setString(1, ""+currentTimeMillis);
			ps.setString(2, JobId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.commit();
		}
		
	}

	@Override
	public LocalJob getLocalJobByJobId(String jobId) {
		PreparedStatement ps;
		ResultSet rs;
		List<LocalJob> jobs = null;
		try {
			ps=JdbcUtils.getConnection().prepareStatement(LocalJobDaoSQL.getLocalJobByJobId);
			ps.setString(1, jobId);
			rs=ps.executeQuery();
			jobs=saveJob(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.commit();
		}
		return jobs.get(0);
	}

}
