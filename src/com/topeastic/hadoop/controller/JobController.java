package com.topeastic.hadoop.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.topeastic.hadoop.entity.Job;
import com.topeastic.hadoop.entity.LocalJob;
import com.topeastic.hadoop.entity.User;
import com.topeastic.hadoop.service.JobService;
import com.topeastic.hadoop.tool.ShellTool;
import com.topeastic.hadoop.utils.Config;
import com.topeastic.hadoop.utils.FtpUtils;
import com.topeastic.hadoop.utils.JobClientConn;

@Controller
public class JobController {

	static final Logger logger = Logger.getLogger(JobController.class);

	@Autowired
	private JobService jobService;

	@RequestMapping("/progress/flash")
	public String flashLocalJob(ModelMap model) {
		System.out.println("调用flash");
		model.put("date", new Date().toLocaleString());
		return "redirect:/progress/showJob";
	}

	@RequestMapping("/progress/runProgress")
	public String runHadoop(ModelMap model, @Valid LocalJob job,
			@RequestParam("fileName") MultipartFile file,
			HttpServletRequest request) {
		logger.info("load 页面");
		User user = (User) request.getSession().getAttribute("currentUser");
		// 文件file不为空
		// 文件系统的 判断
		// 对输入路径 和输出路径的 判断
		String inputPath ="/user/"+user.getUserName()+job.getJobInputPath();
		String outputPath = "/user/"+user.getUserName()+job.getJobOutputPath();
		if (!inputFile(inputPath)) {
			model.put("inputErr", "要解析的文件 不存在~！！");
			model.put("job", job);
			return "/progress/addJar";
		}
		if (outputPath(outputPath)) {
			model.put("outputErr", "输出结果文件目录已存在！");
			model.put("job", job);
			return "/progress/addJar";
		}

		if (!file.isEmpty()) {
			logger.info("jobName:" + job.getJobName());
			// 任务启动界面
			logger.info("开始加载文件....");
			// 文件保存目录
			logger.info("文件名称：" + file.getOriginalFilename());
			// System.out.println("读取配置文件的根目录："+Config.savePath);
			logger.info("名称：" + file.getName() + "\\" + file.getContentType());

			String savePath = Config.savePath + file.getOriginalFilename();
			logger.info("文件保存目录：" + savePath);
			LocalJob localJob = new LocalJob();
			String date = "" + System.currentTimeMillis();
			localJob.setId(date);
			localJob.setJobUserId(user.getUserId());
			localJob.setJobClassName(job.getJobClassName());
			localJob.setJobId(user.getUserName() + "_" + date);
			localJob.setJobInputPath(job.getJobInputPath());
			localJob.setJobJarName(savePath);
			localJob.setJobName(job.getJobName());
			localJob.setJobOutputPath(job.getJobOutputPath());
			localJob.setJobStartTime(date);
			localJob.setJobUserName(user.getUserName());
			localJob.setJobState("4");
			jobService.addLocalJob(localJob);
			// 文件上传至linux本地
			// file.transferTo(saveFile);

			FtpUtils.uploadFileFromProduction(Config.ip, 21, Config.user,
					Config.passwd, Config.savePath, file);
			// hadoop 执行 启动
			logger.info("文件上传成功.....");
			try {
				jobService.startJob(savePath, job.getJobClassName(),
						inputPath, outputPath,
						localJob.getId());
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		// mv.addObject("JobStatus[]", HJobs);
		return "redirect:/progress/selectProgress";
	}

	/**
	 * 分析输出结果目录 是否重复
	 * 
	 * @param outputPath
	 * @return
	 */
	public boolean outputPath(String outputPath) {
		FileSystem fs = JobClientConn.getFs();
		Path path = new Path(outputPath);
		try {
			return fs.exists(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断分析文件源 目录或者文件是否存在
	 * 
	 * @param inputPath
	 * @return
	 */
	public boolean inputFile(String inputPath) {
		FileSystem fs = JobClientConn.getFs();
		Path path = new Path(inputPath);
		try {
			return fs.exists(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@RequestMapping("/progress/selectProgress")
	public String selectProgress(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		// 查询所有的LocalJob

		logger.info("进入 job页面");
		User user = (User) request.getSession().getAttribute("currentUser");
		logger.info("用户信息为:" + user.toString());
		logger.info("开始 查询Job");
		switch (user.getUserRoler()) {
		// 如果为管理员
		case "0":
			// 对全盘hadoop_job数据库查询
			List<LocalJob> jobs = jobService.getLocalRunningJob();
			model.put("RunningJobs", jobs);
			model.put("CompletedJobs", jobService.getLocalCompletedJob());
			break;
		// 如果为普通用户
		case "1":
			// 针对个别用户查询
			logger.info("查询用户：" + user.getUserName());
			model.put("RunningJobs", jobService.getLocalRunningJob(user));
			model.put("CompletedJobs", jobService.getLocalCompletedJob(user));
			break;
		// 如果为超级管理员
		case "2":
			// 查询集群的全部Job
			model.put("jobs", jobService.getAllJob());
			break;
		default:
			break;
		}
		return "/progress/selectProgress";
	}

	@RequestMapping("/progress/showJobInfo")
	public String showJobInfo(ModelMap model,
			@RequestParam("jobId") String jobId) {
		LocalJob job=jobService.getLocalJobByJobId(jobId);
		model.put("job", job);
		return "/progress/showJobInfo";
	}

	// 开始执行Job
	public void startJob(Job job, String savePath) {
		
		// HadoopJob的执行其实就是远程调用linux命令
		// 格式： hadoop jar xxx.jar
		String[] params = { "/user/file", "/user/result" };
		String cmd = "hadoop jar " + savePath + " " + params[0] + " "
				+ params[1];
		ShellTool tool = new ShellTool("119.29.94.71", "linghuiguo",
				"lhg@2015.11", "utf-8");
		tool.exec(cmd);
		
		Thread start = new Thread();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
