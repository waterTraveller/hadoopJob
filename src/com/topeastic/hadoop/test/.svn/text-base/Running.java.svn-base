package com.topeastic.hadoop.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Running {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Process p = Runtime.getRuntime().exec(args[0]);
			
			BufferedReader bReader = null;
			InputStreamReader sReader = null;
			/* 为"错误输出流"单独开一个线程读取之,否则会造成标准输出流的阻塞 */
			Thread t = new Thread(new InputStreamRunnable(p.getErrorStream(),
					"ErrorStream"));
			t.start();
			/* "标准输出流"就在当前方法中读取 */
			Thread in=new Thread(new InputStreamRunnable(p.getInputStream(),
					"InputStream"));
			in.start();
			p.waitFor();
			p.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

/** 读取InputStream的线程 */
class InputStreamRunnable implements Runnable {
	BufferedReader bReader = null;
	String type = null;

	public InputStreamRunnable(InputStream is, String _type) {
		try {
			bReader = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(is), "UTF-8"));
			type = _type;
		} catch (Exception ex) {
		}
	}

	public void run() {
		String line;
		try {
			while ((line = bReader.readLine()) != null) {
				// Thread.sleep(200);
				System.out.println(line);
			}
			bReader.close();
		} catch (Exception ex) {
		}
	}
}
