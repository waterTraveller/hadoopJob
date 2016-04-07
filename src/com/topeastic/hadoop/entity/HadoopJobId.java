package com.topeastic.hadoop.entity;

import java.lang.reflect.Array;

public class HadoopJobId {

	public static String[] hadoopJobId;
	
	public static void addJobId(String jobId){
		String [] result=new String[hadoopJobId.length+1];
		System.arraycopy(hadoopJobId, 0, result, 0, hadoopJobId.length);
		result[result.length-1]=jobId;
		hadoopJobId=result;
	}
	
	public static Boolean isChange(String[] JobId){
		if(JobId.length<hadoopJobId.length){
			return true;
		}
		return false;
	}
	
}
