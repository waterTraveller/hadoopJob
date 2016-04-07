package com.topeastic.hadoop.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.topeastic.hadoop.thread.StartThread;

public class ThreadTest {
	static final Logger logger = Logger.getLogger(ThreadTest.class);

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		StartThread t = new StartThread("/opt/hadoop_jobs/WordCount.jar",
				"com.topeastic.hadoop.test.WordCount", "/user/file.txt",
				"/user/test2","1458812157317");
		Thread th = new Thread(t);
		th.start();
	}

	@Test
	public void threadtest() {
	}

}
