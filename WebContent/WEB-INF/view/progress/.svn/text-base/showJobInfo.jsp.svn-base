<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
/* var dingshi = setInterval("getJob()",2000); */
/* var test = setInterval("javascript:location.href='showJob'",2000); */
function getJob(){
	$.post('showJob',function(data){
		var html=data;
		
		var a = $("#a").html();
		alert(a);
		
		alert($(data).find('div').html());
		/* a = a+"</br>"+data.date;
		$("#a").html(a); */ 
	});
}
function flash(){
	window.location.reload();
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All JOB</title>
</head>
<body>
	<div id="a">${date}</div>
	<a>时间为：${date}</a>
	<button onclick="flash()">刷新</button>
	
	
</body>
</html>