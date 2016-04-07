package com.topeastic.hadoop.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import javax.swing.JTextArea;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

public class test {
	private static Session session;
	private static PrintWriter out;
	static ReadThread rt;
	static ReadThread rt2;
	static OutputStream outStream;
	private static Connection conn;
	private static String ipAddr;
	private static String charset = Charset.defaultCharset().toString();
	private static String userName;
	private static String password;

	public test(String ipAddr, String userName, String password, String charset) {
		test.ipAddr = ipAddr;
		test.userName = userName;
		test.password = password;
		if (charset != null) {
			test.charset = charset;
		}
	}

	public static boolean  login() throws IOException {
		conn = new Connection(ipAddr);
		conn.connect(); // 连接
		return conn.authenticateWithPassword(userName, password); // 认证
	}

	private static void shellRun(String hostname, String username, String password,
			OutputStream outStream, JTextArea jta) {
		test.outStream = outStream;
		// InputStream result=null;
		try {
			if (login()) {
				session = conn.openSession();
				session.requestPTY("bash");
				session.startShell();
				InputStream in = session.getStdout();

				// result=new StreamGobbler(in)
				rt = new ReadThread(in, new PrintStream(outStream), jta);
				rt2 = new ReadThread(session.getStderr(), new PrintStream(
						outStream), jta);
				rt.start();
				rt2.start();
			}
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			ex.printStackTrace();
		}
	}

	public static void executeCommand(String hostname, String username,
			String password, String command) {
		executeCommand(hostname, username, password, command, System.out, null);
	}

	/**
	 * use these instead of
	 * 
	 * @param command
	 * @param command2
	 * @param password
	 * @param username
	 * @param outStream
	 */
	public static void executeCommand(String hostname, String username,
			String password, String command, OutputStream outStream,
			JTextArea jta) {
		if (session == null)
			shellRun(hostname, username, password, outStream, jta);
		if (!command.equals("")) {
			out.println(command);
		}
		out.flush();
		if (command.endsWith("bye")) {
			closeShell();
		}
	}

	private static void closeShell() {
		session.close();
		session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF
				| ChannelCondition.EXIT_STATUS, 30000);
		System.out.println("byebye");
	}

	/**
	 * test
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// Scanner scan=new Scanner(System.in);
//		String cmd = "su;hadoop;hostnamectl set-hostname haha";
//		cmd="help";
//		String hostname = "192.168.7.56";
//		String username = "root";
//		String password = "sun";
//		String[] words = cmd.split(";");
//		for (String command : words) {
//			test.executeCommand(hostname, username, password, command);
//		}
//		test.closeShell();
		/*
		 * while(scan.hasNext()){
		 * test.executeCommand(hostname,username,password,scan.nextLine()); }
		 */
	}
}

class ReadThread extends Thread {
	private InputStream in;
	private PrintStream out;
	private Temp temp=new Temp(out);
	private String charset;
	private JTextArea jta;
	private boolean flag = true;
	
	public void stopThread() {
		flag = false;
	}

	public ReadThread(InputStream in, PrintStream out, String charset) {
		this.out = out;
		this.in = in;
		this.charset = charset;
	}

	public ReadThread(InputStream in, PrintStream out, JTextArea jta,
			String charset) {
		this.out = out;
		this.jta = jta;
		this.in = in;
		this.charset = charset;
	}

	public ReadThread(InputStream in, PrintStream out, JTextArea jta) {
		this(in, out, jta, "utf-8");
	}

	public ReadThread(InputStream in, PrintStream out) {
		this(in, out, "utf-8");
	}

	public ReadThread(InputStream in, String charset) {
		this(in, System.out, charset);

	}

	public ReadThread(InputStream in) {
		this(in, "utf-8");
	}

	@Override
	public void run() {
		BufferedReader br;
		
		try {
			br = new BufferedReader(new InputStreamReader(in, charset));
			String temp;
			while ((temp = br.readLine()) != null && flag == true) {
				this.temp.ThrowTemp(temp);
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


class Temp extends PrintWriter {
	

	public Temp(OutputStream out) {
		super(out);
		// TODO Auto-generated constructor stub
	}

	private  String temp ;
	
	public String getTemp(){
		return this.temp;
	}
	
	
	public void ThrowTemp(String temp){
		this.temp=temp;
		
	}
}
