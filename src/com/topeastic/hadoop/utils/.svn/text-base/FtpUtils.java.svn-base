package com.topeastic.hadoop.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

//import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

public class FtpUtils {

	/*
	 * // 服务器的用户名 private static String userName; // 服务器的密码 private static
	 * String passwd; // 服务器的ip private static String ip; // 服务器的端口 private
	 * static int port;
	 * 
	 * private static FTPClient ftpClient = null;
	 * 
	 * static {
	 * 
	 * try { userName = PropertiesUtils.getConfigProperty("ftpUserName"); passwd
	 * = PropertiesUtils.getConfigProperty("ftpPasswd"); ip =
	 * PropertiesUtils.getConfigProperty("ftpIp"); port = Integer
	 * .valueOf(PropertiesUtils.getConfigProperty("ftpPort"));
	 * 
	 * } catch (Exception e) {
	 * 
	 * }
	 * 
	 * }
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String hostname = "119.29.92.64";
		int port = 21;
		String username = "root";
		String password = "Password$$!@123";
		String pathname = "/opt";
		String filename = "test.jar";
		String originfilename = "d:\\Users\\Administrator\\Desktop\\data";
		System.out.println("开始上传："+(new Date(System.currentTimeMillis()).toLocaleString()));
//		uploadFileFromProduction(hostname, port, username, password, pathname,originfilename);
		System.out.println("上传结束："+(new Date(System.currentTimeMillis()).toLocaleString()));
	}

	/**
	 * 上传文件（可供Action/Controller层使用）
	 * 
	 * @param hostname
	 *            FTP服务器地址
	 * @param port
	 *            FTP服务器端口号
	 * @param username
	 *            FTP登录帐号
	 * @param password
	 *            FTP登录密码
	 * @param pathname
	 *            FTP服务器保存目录
	 * @param fileName
	 *            上传到FTP服务器后的文件名称
	 * @param inputStream
	 *            输入文件流
	 * @return
	 */
	public static boolean uploadFile(String ip, int port, String username,
			String password, String pathname, String fileName,
			InputStream inputStream) {
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("UTF-8");
		try {
			
			// 连接FTP服务器
			ftpClient.connect(ip, port);
//			ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
			// 登录FTP服务器
			ftpClient.login(username, password);
			// 是否成功登录FTP服务器
			// 看返回的值是不是230，如果是，表示登陆成功 
			int replyCode = ftpClient.getReplyCode();
			System.out.println("replyCode:"+replyCode);
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return flag;
			}

			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.storeFile(fileName, inputStream);
			inputStream.close();
			ftpClient.logout();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 上传文件（不可以进行文件的重命名操作）
	 * 
	 * @param hostname
	 *            FTP服务器地址
	 * @param port
	 *            FTP服务器端口号
	 * @param username
	 *            FTP登录帐号
	 * @param password
	 *            FTP登录密码
	 * @param pathname
	 *            FTP服务器保存目录
	 * @param originfilename
	 *            待上传文件的名称（绝对地址）
	 * @return
	 */
	public static boolean uploadFileFromProduction(String hostname, int port,
			String username, String password, String pathname,
			MultipartFile file) {
		boolean flag = false;
		try {
			String fileName = file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			flag = uploadFile(hostname, port, username, password, pathname,
					fileName, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 删除文件
	 * 
	 * @param hostname
	 *            FTP服务器地址
	 * @param port
	 *            FTP服务器端口号
	 * @param username
	 *            FTP登录帐号
	 * @param password
	 *            FTP登录密码
	 * @param pathname
	 *            FTP服务器保存目录
	 * @param filename
	 *            要删除的文件名称
	 * @return
	 */
	public static boolean deleteFile(String hostname, int port,
			String username, String password, String pathname, String filename) {
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		try {
			// 连接FTP服务器
			ftpClient.connect(hostname, port);
			// 登录FTP服务器
			ftpClient.login(username, password);
			// 验证FTP服务器是否登录成功
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return flag;
			}
			// 切换FTP目录
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.dele(filename);
			ftpClient.logout();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
				} catch (IOException e) {

				}
			}
		}
		return flag;
	}

	/**
	 * 下载文件
	 * 
	 * @param hostname
	 *            FTP服务器地址
	 * @param port
	 *            FTP服务器端口号
	 * @param username
	 *            FTP登录帐号
	 * @param password
	 *            FTP登录密码
	 * @param pathname
	 *            FTP服务器文件目录
	 * @param filename
	 *            文件名称
	 * @param localpath
	 *            下载后的文件路径
	 * @return
	 */
	public static boolean downloadFile(String hostname, int port,
			String username, String password, String pathname, String filename,
			String localpath) {
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		try {
			// 连接FTP服务器
			ftpClient.connect(hostname, port);
			// 登录FTP服务器
			ftpClient.login(username, password);
			// 验证FTP服务器是否登录成功
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return flag;
			}
			// 切换FTP目录
			ftpClient.changeWorkingDirectory(pathname);
			FTPFile[] ftpFiles = ftpClient.listFiles();
			for (FTPFile file : ftpFiles) {
				if (filename.equalsIgnoreCase(file.getName())) {
					File localFile = new File(localpath + "/" + file.getName());
					OutputStream os = new FileOutputStream(localFile);
					ftpClient.retrieveFile(file.getName(), os);
					os.close();
				}
			}
			ftpClient.logout();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
				} catch (IOException e) {

				}
			}
		}
		return flag;
	}

}
