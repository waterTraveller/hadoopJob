package com.topeastic.hadoop.test;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HadoopJarTest {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			startJob("", "", "", "");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void startJob(String jarFile, String className, String input,
			String output) throws Throwable {
		String args[] = { jarFile, className, input, output };
		String mainClassName = "org.apache.hadoop.util.RunJar";
		Class<?> mainClass = Class.forName(mainClassName);
		Method main = mainClass.getMethod("main", new Class[] { Array
				.newInstance(String.class, 0).getClass() });
		try {
			main.invoke(null, new Object[] { args });
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}

}
