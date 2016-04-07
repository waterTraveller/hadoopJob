<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<!-- <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<script type="text/javascript" src="../../../js/jquery-1.12.0.min.js"></script>
<script language="javascript" type="text/javascript">
	$(function() {
		$('#submit1').click(function() {
			var formData = $('#form1').serialize();
			alert(formData);
			$.ajax({
				url : 'addJar',
				type : "POST",
				data : formData,
				success : function(r) {
					alert(r.status);
					if (r.status == 'success') {
						/* window.location.href = "addJar"; */
						alert("成功");
					} else {
						alert('失败');
						window.location.href = "addJar";
					}
				}
			});
		});
	});

	function next() {
		alert("next()方法执行");
		alert($("#test2").val());
		var formData = $('#form1').serialize();
		alert($('#form1').serialize());
		alert('asdas');
		$.ajax({
			url : 'addJar',
			type : "POST",
			data : formData,
			success : function(r) {
				if (r.status == 'success') {
					window.location.href = "addJar";
				} else {
					alert('失败');
				}
			}
		});

	}
</script> -->
<title>Add Progress</title>
</head>
<body>
	<h1>this is the page of Set Progress</h1>
	<span>任务设置</span>

	<form id="form1" action="addJar">
		<table border="1">
			<tr>
				<td>Job名称：</td>
				<td><input type="text" name="jobName" id="test" value="ooo"></td>
			</tr>
			<tr>
				<td>数据输入地址：</td>
				<td><input type="text" name="jobInputPath" id="test2"></td>
			</tr>
			<tr>
				<td>数据输出地址：</td>
				<td><input type="text" name="jobOutputPath"></td>
			</tr>
			<tr>
				<td>开始的类名:</td>
				<td><input type="text" name="jobClassName"></td>
			</tr>
			<tr>
				<td>任务参数四:</td>
				<td><input type="text"></td>
			</tr>
		</table>
		<input type="submit" value="下一个步骤" id="submit1">
		<span style="color: red;">${inputErr}</span>
	</form>

</body>

</html>