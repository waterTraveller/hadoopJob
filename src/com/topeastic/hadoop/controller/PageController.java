package com.topeastic.hadoop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.topeastic.hadoop.entity.Job;
import com.topeastic.hadoop.entity.LocalJob;
import com.topeastic.hadoop.tool.ShellTool;
import com.topeastic.hadoop.utils.Config;
import com.topeastic.hadoop.utils.StringUtils;

@Controller
public class PageController {
	
	static final Logger logger=Logger.getLogger(PageController.class);

	@RequestMapping("welcome")
	public String welcome() {
		System.out.println("执行welcome");
		return "/welcome";
	}

	@RequestMapping("progress/setProgress")
	public String setProgress() {
		
		return "/progress/setProgress";

	}

	@RequestMapping("progress/addJar")
	public String addJar(ModelMap model,HttpServletRequest request) {
		// String savePathDir="/file";
		// String saveRealPathDir = request.getSession().getServletContext()
		// .getRealPath(savePathDir);
		// System.out.println("文件目录为："+saveRealPathDir);
		ModelAndView mv = new ModelAndView();
		// Job job=(Job) request.getAttribute("job");
		String jobName = request.getParameter("jobName");
		String jobInputPath = request.getParameter("jobInputPath");
		String jobOutputPath = request.getParameter("jobOutputPath");
		String jobClassName = request.getParameter("jobClassName");
		
		if(StringUtils.strIsNotNull(jobInputPath)&&StringUtils.strIsNotNull(jobOutputPath)&&StringUtils.strIsNotNull(jobClassName)){
			logger.info(jobName + "|" + jobInputPath + "|" + jobOutputPath
					+ "|" + jobClassName);
			LocalJob job=new LocalJob();
			job.setJobName(jobName);
			job.setJobInputPath(jobInputPath);
			job.setJobOutputPath(jobOutputPath);
			job.setJobClassName(jobClassName);
			logger.info("222" + job.toString());
			logger.info("addJar Method ");
			model.put("job", job);
			String strPathFile = request.getSession().getServletContext().getRealPath(request.getRequestURI());
			logger.info("文件的绝对路径:" + strPathFile + "<br/>");
			return "/progress/addJar";
		}else{
			model.put("inputErr", "文件输入地址  文件输出地址  开始的类名 的值非法");
			return "/progress/setProgress";
		}
		
		// return "/progress/setProgress";
	}

	private void startThread(String jobName) {
		// TODO Auto-generated method stub

	}

	// 开始执行Job
	public void startJob(Job job, String savePath) {
		// HadoopJob的执行其实就是远程调用linux命令
		// 格式： hadoop jar xxx.jar
		String[] params = { "/user/file", "/user/result6" };
		String cmd = "hadoop jar " + savePath + " " + params[0] + " "
				+ params[1];
		ShellTool tool = new ShellTool("119.29.94.71", "linghuiguo",
				"lhg@2015.11", "utf-8");
		tool.exec(cmd);
	}

	/**
	 * 对控件文件的处理
	 * 
	 * @param request
	 */
	public String fileHandle(MultipartFile multipartFile,
			HttpServletRequest request) {
		System.out.println("文件处理开始.........");
		// 创建保存该文件的目录地址
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHH");
		String savePathDir = "/upload/" + dateformat.format(new Date());
		/** 得到文件保存目录的真实路径* */
		String saveRealPathDir = request.getSession().getServletContext()
				.getRealPath(savePathDir);
		System.out.println("文件保存目录....." + saveRealPathDir);
		/** 根据真实路径创建目录* */
		File logoSaveFile = new File(saveRealPathDir);
		if (!logoSaveFile.exists())
			logoSaveFile.mkdirs();
		// 页面控件的文件流
		// MultipartFile multipartFile = request.getFile("file");

		// /** 获取文件的后缀* */
		// String suffix = multipartFile.getOriginalFilename().substring(
		// multipartFile.getOriginalFilename().lastIndexOf("."));
		// /** 使用UUID生成文件名称* */
		// String saveName = UUID.randomUUID().toString() + suffix;// 构建文件名称
		System.out.println(multipartFile.getName());
		String saveName = multipartFile.getOriginalFilename();
		System.out.println("文件名字：" + saveName);
		/** 拼成完整的文件保存路径加文件* */
		String fileName = saveRealPathDir + File.separator + saveName;
		System.out.println("完整路径：" + fileName);
		File file = new File(fileName);
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/** 打印出上传到服务器的文件的绝对路径* */
		System.out.println("****************" + fileName + "**************");

		return fileName;
	}

	@RequestMapping(value = "/loading")
	public ModelAndView uploadFile(
			@RequestParam(value = "file") MultipartFile file) {
		System.out.println("load 页面");
		if (!file.isEmpty()) {
			FileOutputStream fos = null;
			try {
				byte[] bytes = file.getBytes();
				fos = new FileOutputStream("D:\\" + file.getOriginalFilename());
				fos.write(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new ModelAndView("message");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
