package com.topeastic.hadoop.test;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.hadoop.util.RunJar;

public class RunJarTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			startJob("/opt/Wordcount.jar","com.topeastic.hadoop.test.WordCount","hdfs://10.104.130.68:9000/user/file", "hdfs://10.104.130.68:9000/user/test");
//			Runtime.getRuntime().exec("source /etc/profile;hadoop jar /opt/test.jar /user/file /user/test").waitFor();
			
			System.out.println("Runtime.getRuntime().exec  执行结束！！！");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void startJob(String jarFile, String input, String output)
			throws Throwable {
		String args[] = { jarFile, input, output };
//		String mainClassName = "org.apache.hadoop.util.RunJar";
//		Class<?> mainClass = Class.forName(mainClassName);
//		Method main = mainClass.getMethod("main", new Class[] { Array
//				.newInstance(String.class, 0).getClass() });
//		try {
//			main.invoke(null, new Object[] { args });
//		} catch (InvocationTargetException e) {
//			throw e.getTargetException();
//		}
		RunJar.main(args);
	}

	public static void startJob(String jarFile, String className, String input,
			String output) throws Throwable {
		String args[] = { jarFile, className, input, output };
//		String mainClassName = "org.apache.hadoop.util.RunJar";
//		Class<?> mainClass = Class.forName(mainClassName);
//		Method main = mainClass.getMethod("main", new Class[] { Array
//				.newInstance(String.class, 0).getClass() });
//		try {
//			main.invoke(null, new Object[] { args });
//		} catch (InvocationTargetException e) {
//			throw e.getTargetException();
//		}
		
		RunJar.main(args);
	}
	
	public static void run(){
		
	}
}
