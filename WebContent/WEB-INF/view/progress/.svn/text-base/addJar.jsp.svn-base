<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript">
	function show(){
		var path=$("#file").val();
		alert(path);
	}
</script>
<title>Add Jar</title>
</head>
<body>
<form action="runProgress" enctype="multipart/form-data" method="post">
	<table border="1">
		<tr>
			<td colspan="2" align="center">任务配置详情</td>
		</tr>
		<tr>
			<td>任务名称：</td>
			<td align="center"><input type="text" name="jobName" value="${job.jobName}"  readonly="readonly"></td>
		</tr>
		<tr>
			<td>数据输入地址：</td>
			<td align="center"><input type="text" name="jobInputPath" value="${job.jobInputPath}" readonly="readonly"></td>
			<td><span style="color: red;">${inputErr}</span></td>
		</tr>
		<tr>
			<td>数据输出地址：</td>
			<td align="center"><input type="text" name="jobOutputPath" value="${job.jobOutputPath}" readonly="readonly"></td>
			<td><span style="color: red;">${outputErr}</span></td>
		</tr>
		<tr>
			<td>开始的类名：</td>
			<td align="center"><input type="text" name="jobClassName" value="${job.jobClassName}" readonly="readonly"></td>
		</tr>
		<tr>
			<td>参数四：</td>
			<td align="center"><input type="text" value=""></td>
		</tr>
		<tr>
			<td>任务本地jar包</td>
			<td align="center"><input id="file" name="fileName" type="file"></td>
		</tr>
	</table>
	<input type="submit" value="Start Job">
	
</form>
<button id="path" onclick="show()">路径</button>
</body>
</html>