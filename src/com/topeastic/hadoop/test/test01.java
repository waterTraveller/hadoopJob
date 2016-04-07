package com.topeastic.hadoop.test;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;

import com.topeastic.hadoop.dao.impl.LocalJobDaoImpl;
import com.topeastic.hadoop.dao.impl.UserDaoImpl;
import com.topeastic.hadoop.entity.LocalJob;
import com.topeastic.hadoop.entity.User;

public class test01 {
	public static void main(String[] args) throws Exception {
		File file=new File("D:\\");
		FileInputStream fis=new FileInputStream(file);
	}
	
	public static String test(){
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<10;i++){
					System.out.println(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		return "return test";
	}
	
	@Test
	public void testAdd(){
		LocalJobDaoImpl dao=new LocalJobDaoImpl();
		LocalJob job=new LocalJob();
		job.setId("2");
		job.setJobUserId("2");
		job.setJobUserName("tom");
		job.setJobId("tom_1");
		job.setJobName("test01");
		job.setJobJarName("/opt/test.jar");
		job.setJobClassName("com.hadoop.WordCount");
		job.setJobInputPath("/user/file");
		job.setJobOutputPath("/user/test01");
		job.setJobState("0");
		job.setJobStartTime("14545484616185");
		job.setJobHadoopId("job_2015_001");
		dao.insertJob(job);
		for(LocalJob s:dao.showAll()){
			System.out.println(s.toString());
		}
	}
	
	@Test
	public void testadduser(){
		UserDaoImpl dao=new UserDaoImpl();
		User user=new User();
		user.setUserId("5");
		user.setUserName("rose");
		user.setUserPasswd("123456");
		user.setUserRoler("1");
		dao.add(user);
	}
}
