package com.topeastic.hadoop.utils;

public class Config {

	public static final String savePath=PropertiesUtils.getConfigProperty("savePath");
	
	public static final String ip=PropertiesUtils.getConfigProperty("ip");
	public static final String user=PropertiesUtils.getConfigProperty("user");
	public static final String passwd=PropertiesUtils.getConfigProperty("passwd");
	public static final String hostName=PropertiesUtils.getConfigProperty("hostName");
	public static final String jobtrackerPort=PropertiesUtils.getConfigProperty("jobtrackerPort");
	public static final String hdfsPort=PropertiesUtils.getConfigProperty("hdfsPort");
	public static final String driverName = PropertiesUtils.getConfigProperty("jdbc.driverclass");
	public static final String url = PropertiesUtils.getConfigProperty("jdbc.url");
	public static final String master = PropertiesUtils.getConfigProperty("master");
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String savePath1=PropertiesUtils.getConfigProperty("savePath");
		System.out.println("savepath:"+savePath1);
	}

}
