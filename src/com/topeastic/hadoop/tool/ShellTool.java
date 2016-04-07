package com.topeastic.hadoop.tool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

public class ShellTool {
	
	static final Logger logger=Logger.getLogger(ShellTool.class);

	private Connection conn;
	private String ipAddr;
	private String charset = Charset.defaultCharset().toString();
	private String userName;
	private String password;

	public ShellTool(String ipAddr, String userName, String password,
			String charset) {
		this.ipAddr = ipAddr;
		this.userName = userName;
		this.password = password;
		if (charset != null) {
			this.charset = charset;
		}
	}

	public boolean login() throws IOException {
		conn = new Connection(ipAddr);
		conn.connect(); // 连接
		return conn.authenticateWithPassword(userName, password); // 认证
	}

	public void exec(String cmds) {
		InputStream in = null;
		InputStream out = null;
		try {
			if (this.login()) {
				Session session = conn.openSession(); // 打开一个会话
				session.execCommand(cmds);
				in = session.getStderr();
				out=session.getStdout();
				processStdout(in, this.charset);
				processStdErr(out,this.charset);
				session.close();
				conn.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void processStdout(InputStream in, String charset) {
		logger.info("err");
		BufferedReader bReader = null;
		String line;
		try {
			bReader = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(in), charset));
			while ((line = bReader.readLine()) != null) {
				logger.info(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void processStdErr(InputStream in, String charset) {
		logger.info("输出");
		BufferedReader bReader = null;
		String line;
		try {
			bReader = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(in), charset));
			while ((line = bReader.readLine()) != null) {
				logger.info(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Thread run = new Thread(new Runnable() {

			@Override
			public void run() {
				ShellTool tool = new ShellTool("192.168.7.56", "root",
						"sun", "utf8");
				// TODO Auto-generated method stub
				// hadoop jar /opt/test.jar /user/file /user/result
				// String
				// result=tool.exec("source /etc/profile;hadoop jar /opt/test.jar /user/file /user/result");
				tool.exec("help");
			}
		});
		run.start();
		System.out.println("asdad");

	}
}
