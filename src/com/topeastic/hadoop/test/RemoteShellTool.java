package com.topeastic.hadoop.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

public class RemoteShellTool {

	private Connection conn;
	private String ipAddr;
	private String charset = Charset.defaultCharset().toString();
	private String userName;
	private String password;

	public RemoteShellTool(String ipAddr, String userName, String password,
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

	public String exec(String cmds) {
		InputStream in = null;
		String result = "";
		try {
			if (this.login()) {
				Session session = conn.openSession(); // 打开一个会话
				session.execCommand(cmds);
				
				in = session.getStdout();
				result = this.processStdout(in, this.charset);
				session.close();
				conn.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public String processStdout(InputStream in, String charset) {
	
		byte[] buf = new byte[1024];
		StringBuffer sb = new StringBuffer();
		try { 
			while (in.read(buf) != -1) {
				sb.append(new String(buf, charset));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		RemoteShellTool tool = new RemoteShellTool("119.29.94.71", "linghuiguo",
				"lhg@2015.11", "utf-8");

		String result = tool.exec("source /etc/profile;hadoop jar /opt/test.jar /user/file /user/test02  第三个参数");
		System.out.println("asdas");
		System.out.print(result);
		System.out.println(new Date(1454069271355L).toLocaleString());

	}

}

