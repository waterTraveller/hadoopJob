package com.topeastic.hadoop.utils;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class JobClientConn {
	
	private static ThreadLocal<JobClient> local = new ThreadLocal<JobClient>();

	private static ThreadLocal<FileSystem> localFs = new ThreadLocal<FileSystem>();
	
	static String ip;
	static String port;
	static JobConf conf;
	static InetSocketAddress jobtracker;
		
	static {
		conf=new JobConf();
		//一些对hdfs操作的时候这个是关键
		conf.set("fs.default.name", "hdfs://"+Config.master+":9000");
		//在获取任务的时候 这个是关键
		jobtracker = new InetSocketAddress(Config.master, 9001);
	}
	
	/**
	 * 连接池 获取
	 * @return
	 */
	public static JobClient getConnection(){
		JobClient connection = local.get();
		if (connection == null) {
			try {
				connection = new JobClient(jobtracker,conf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			local.set(connection);
		}
		return connection;
	}
	
	public static void closeJobClient(){
		JobClient connection = local.get();
		if(connection!=null){
			try {
				connection.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			local.set(null);
		}
	}
	
	/**
	 * 获取FS文件系统
	 * @return
	 */
	public static FileSystem getFs(){
		FileSystem fs=localFs.get();
		if(fs==null){
			try {
				fs=FileSystem.get(conf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			localFs.set(fs);
		}
		return fs;
	}
	
	public static void closeFs(){
		FileSystem fs=localFs.get();
		if(fs!=null){
			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			localFs.set(null);
		}
	}
	
}