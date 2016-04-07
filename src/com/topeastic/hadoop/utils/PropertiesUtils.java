package com.topeastic.hadoop.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取Properties文件中配置项
 * 
 */
public class PropertiesUtils {

	public static String getConfigProperty(String key) {
		Properties prop = new Properties();
		String rootPath=new PropertiesUtils().getClass().getResource("/").getPath();
		String file_path = rootPath + "config.properties";
		FileInputStream in;
		String value = null;
		try {
			in = new FileInputStream(file_path);

//			System.out.println(in == null);
			// InputStream in
			// =ClassLoader.getSystemResourceAsStream("config.properties");
			prop.load(in);
			value = prop.getProperty(key).trim();
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(Config.ip);
		System.out.println(Config.hostName);
		System.out.println(Config.jobtrackerPort);
		System.out.println(Config.hdfsPort);
	}
	/**
	 * 
	 * @param propertiesName
	 *            文件路径 现在默认是在项目的 WEB_INF 下面
	 * @return
	 */
	public static Properties getPropertiesofWebInfo(String propertiesName)
			throws Exception {
		String path = PropertiesUtils.class.getClassLoader().getResource("")
				.toURI().getPath();
		Properties prop = new Properties();
		FileInputStream in = new FileInputStream(path + "../" + propertiesName);
		prop.load(in);
		in.close();
		return prop;
	}

	// 获得src 下面的properties
	public static Properties getPropertiesOfSrc(String propertiesName)
			throws Exception {
		Properties prop = new Properties();
		InputStream in = PropertiesUtils.class.getClassLoader()
				.getResourceAsStream(propertiesName);
		prop.load(in);
		in.close();
		return prop;
	}

	/**
	 * 快速获得一个properties 里面的值
	 * 
	 * @param propertiesName
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getPropertiesValue(String propertiesName, String key)
			throws Exception {
		String path = PropertiesUtils.class.getClassLoader().getResource("")
				.toURI().getPath();
		Properties prop = new Properties();
		FileInputStream in = new FileInputStream(path + "../" + propertiesName);
		prop.load(in);
		in.close();
		return prop.getProperty(key);
	}

	/**
	 * 读写property文件
	 * 
	 * @return Property对象
	 * */
	public static String getConfigValue(String key) {
		FileInputStream fis = null;
		Properties prop = new Properties();
		try {
			String path = PropertiesUtils.class.getClassLoader()
					.getResource("").toURI().getPath();
			fis = new FileInputStream(new File(path + "../jdbc.properties"));
			prop.load(fis);
			fis.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return prop.getProperty(key);
	}

}
