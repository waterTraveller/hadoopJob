//package com.topeastic.hadoop.test;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.lang.reflect.Array;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.Types;
//import java.util.Enumeration;
//import java.util.Hashtable;
//import java.util.StringTokenizer;
//
//import org.apache.commons.codec.Charsets;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//
//import com.mysql.jdbc.ResultSetImpl;
//
//public class HadoopRows {
//
//	public static void hadoopRows(String jarFile, String className,
//
//	String hadoopInput, String hadoopOutput, ResultSet rset[])
//
//	throws Exception {
//
//		System.out.println("Hadoop Job Strating......");
//
//		try {
//
//			runHadoopMR(jarFile, className, hadoopInput, hadoopOutput);
//
//		} catch (Throwable e) {
//
//			e.printStackTrace();
//
//		}
//
//		System.out.println("Hadoop Job end......");
//
//		// 设置结果集的元数据信息
//
//		ResultSetMetaDataImpl rsmd = new ResultSetMetaDataImpl(2);
//
//		rsmd.setColumnType(1, Types.VARCHAR);
//
//		rsmd.setColumnName(1, "word");
//
//		rsmd.setColumnLabel(1, "word");
//
//		rsmd.setColumnDisplaySize(1, 255);
//
//		rsmd.setTableName(1, "MyTable");
//
//		rsmd.setColumnType(2, Types.INTEGER);
//
//		rsmd.setColumnName(2, "wordcount");
//
//		rsmd.setColumnLabel(2, "wordcount");
//
//		rsmd.setTableName(2, "MyTable");
//
//		ResultSetImpl rs = null;
//
//		try {
//
//			rs = new ResultSetImpl((ResultSetMetaData) rsmd);
//
//			rs.beforeFirst(); // Make sure we are at the beginning.
//
//		} catch (Exception e) {
//
//			System.out.println("Error: couldn't create result set.");
//
//			System.out.println(e.toString());
//
//		}
//
//		// 访问HDFS文件系统得到MapRedeuce计算结果文件
//
//		Hashtable<String, Integer> hadoopTable =
//
//		hadoopGetRows(hadoopOutput);
//
//		Enumeration<String> words = hadoopTable.keys();
//
//		int row = 0;
//
//		// 把结果集合以SQL记录的形式封装成SQL结果集
//
//		while (words.hasMoreElements()) {
//
//			try {
//
//				row++;
//
//				String word = (String) words.nextElement();
//
//				int wordCount = ((Integer) hadoopTable.get(word)).intValue();
//
//				rs.insertRow();
//
//				rs.updateString(1, word);
//
//				System.out.print(word + "    ");
//
//				rs.updateInt(2, wordCount);
//
//				System.out.println(wordCount);
//
//			} catch (Exception e) {
//
//				System.out.println("Error: couldn't insert row/data on row "
//
//				+ row);
//
//				System.out.println(e.toString());
//
//			}
//
//		}
//
//		try {
//
//			rs.beforeFirst();
//
//		} catch (Exception e) {
//
//			System.out.println(e.toString());
//
//		}
//
//		rset[0] = rs;
//
//	}
//
//	private static Hashtable<String, Integer> hadoopGetRows(String hadoopOutput)
//
//	throws Exception {
//
//		Configuration conf = new Configuration();
//
//		conf.set("fs.defaultFS", "hdfs://HAIQ-DB-01:8020");
//
//		conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
//
//		FileSystem fs = FileSystem.get(conf);
//
//		Path file = new Path(new Path(hadoopOutput), "part-r-00000");
//
//		if (!fs.exists(file)) {
//
//			throw new IOException("Output not found!");
//
//		}
//
//		BufferedReader br = null;
//
//		Hashtable<String, Integer> hadoopTable = new Hashtable<String, Integer>();
//
//		try {
//
//			br = new BufferedReader(new InputStreamReader(fs.open(file),
//
//			Charsets.UTF_8));
//
//			String line;
//
//			while ((line = br.readLine()) != null) {
//
//				StringTokenizer st = new StringTokenizer(line);
//
//				String word = st.nextToken();
//
//				String wordCount = st.nextToken();
//
//				hadoopTable.put(word, Integer.parseInt(wordCount));
//
//			}
//
//		} finally {
//
//			if (br != null) {
//
//				br.close();
//
//			}
//
//			if (fs != null) {
//
//				fs.close();
//
//			}
//
//		}
//
//		return hadoopTable;
//
//	}
//
//	private static void runHadoopMR(String jarFile, String className,
//
//	String input, String output) throws Throwable {
//
//		String args[] = { jarFile, className, input, output };
//
//		String mainClassName = "org.apache.hadoop.util.RunJar";
//
//		Class<?> mainClass = Class.forName(mainClassName);
//
//		Method main = mainClass.getMethod("main", new Class[] { Array
//
//		.newInstance(String.class, 0).getClass() });
//
//		try {
//
//			main.invoke(null, new Object[] { args });
//
//		} catch (InvocationTargetException e) {
//
//			throw e.getTargetException();
//
//		}
//
//	}
//
//	public static void main(String args[]) {
//
//		ResultSet[] rset = new ResultSetImpl[1];
//
//		try {
//
//			hadoopRows("/opt/sybiq/16.0/IQ-16_0/udf/java/lib/myhadoop.jar",
//
//			"hadoop.example.MyWordCount",
//
//			"/user/sybiq/input/*.txt",
//
//			"/user/sybiq/output/", rset);
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		}
//
//	}
//
//}