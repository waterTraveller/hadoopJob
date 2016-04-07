<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page 
import="java.util.Date" 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Select Progress</title>
</head>
<body>
	<h1>this is the view of hadoop progress</h1>
	<%-- <a>${HJob.jobName}</a> --%>

	<h2>Running Job</h2>
	<table border="1">
	
		<tr>
			<td>Job Id</td>
			<td>Job Name</td>
			<td>Job UserName</td>
			<td>Job JarName</td>
			<td>Job ClassName</td>
			<td>Job InputPath</td>
			<td>Job OutputPath</td>
			<td>Job State</td>
			<td>Job StartTime</td>
		</tr>
		<c:forEach var="Rjobs" items="${RunningJobs}">
			<tr>
				<td><a href="showJobInfo?jobId=${Rjobs.jobId}">${Rjobs.jobId}</a></td>
				<td>${Rjobs.jobName}</td>
				<td>${Rjobs.jobUserName}</td>
				<td>${Rjobs.jobJarName}</td>
				<td>${Rjobs.jobClassName}</td>
				<td>${Rjobs.jobInputPath}</td>
				<td>${Rjobs.jobOutputPath}</td>
				<td>
					<c:choose>
						<c:when test="${Rjobs.jobState==1}">运行中</c:when>
						<c:when test="${Rjobs.jobState==2}">成功</c:when>
						<c:when test="${Rjobs.jobState==3}">失败</c:when>
						<c:when test="${Rjobs.jobState==4}">准备中</c:when>
						<c:when test="${Rjobs.jobState==5}">杀死</c:when>
					</c:choose>
				</td>
				<td>${Rjobs.jobStartTime}</td>
			</tr>
		</c:forEach>
	</table>

	<h2>Completed Job</h2>
	<table border="1">
		<tr>
			<td>Job Id</td>
			<td>Job Name</td>
			<td>Job UserName</td>
			<td>Job JarName</td>
			<td>Job ClassName</td>
			<td>Job InputPath</td>
			<td>Job OutputPath</td>
			<td>Job State</td>
			<td>Job StartTime</td>
			<td>Job EndTime</td>
		</tr>
		<c:forEach var="Cjobs" items="${CompletedJobs}">
			<tr>
				<td><a href="showJobInfo?jobId=${Cjobs.jobId}">${Cjobs.jobId}</a></td>
				<td>${Cjobs.jobName}</td>
				<td>${Cjobs.jobUserName}</td>
				<td>${Cjobs.jobJarName}</td>
				<td>${Cjobs.jobClassName}</td>
				<td>${Cjobs.jobInputPath}</td>
				<td>${Cjobs.jobOutputPath}</td>
				<td>
					<c:choose>
						<c:when test="${Cjobs.jobState==1}">运行中</c:when>
						<c:when test="${Cjobs.jobState==2}">成功</c:when>
						<c:when test="${Cjobs.jobState==3}">失败</c:when>
						<c:when test="${Cjobs.jobState==4}">准备中</c:when>
						<c:when test="${Cjobs.jobState==5}">杀死</c:when>
					</c:choose>
				</td>
				<td>${Cjobs.jobStartTime}</td>
				<td>${Cjobs.jobEndTime}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>