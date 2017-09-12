<%@page
	import="org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/list.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未签到学生列表</title>
</head>
<body>
	<br>
	<h4>学生列表</h4>
	<table class="table">
		<tr>
			<td>姓名</td>
			<td>学号</td>
			
	</tr>
		<c:forEach items="${aList}" var="Student">
			<tr>
				<td>${Student.stuNum}</td>
				<td>${Student.stuName}</td>
				
			</tr>

		</c:forEach>

</table>
	
	<br>
	<div style="text-align: center; font-size: 18px;">
	<a>共有${count }条记录，当前第${pageNo}/${totalPage}页</a>
		<a href="StudentAction?operate=listStuNo&pageNo=1">首页</a> <a
		href="StudentAction?operate=listStuNo&pageNo=${pageNo- 1}">上一页</a> <a
		href="StudentAction?operate=listStuNo&pageNo=${pageNo+ 1}">下一页</a> <a
		href="StudentAction?operate=listStuNo&pageNo=${totalPage}">尾页</a> <a
		href="<%=request.getContextPath()%>/StudentAction?operate=listStuYes">已签到名单</a>

</div>
</body>
</html>