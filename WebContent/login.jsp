<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/default.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

	<div class="htmleaf-container"><header class="htmleaf-header">
		<h1>17级集训队签到网站</h1>
	</header>
		<div class="wrapper">
			<div class="container">
				<h1>Welcome</h1>

				<form class="form" method="post"
				action='<c:url value="/StudentAction?operate=sign"></c:url>'>
				<input type="hidden" name="sessionName" value="${stuName }"/>
					 <input
					type="text" placeholder="学号" name="stuNum" required />
					<input type="text" placeholder="姓名" name="stuName" required />
					<button type="submit" id="login-button">签到</button> <br />

			</form> <c:if test="${not empty msg }">
					<script type="text/javascript">
						alert("${msg}");
					</script>
				</c:if>
		</div>

			<ul class="bg-bubbles">
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
		</ul>
	</div></div>

</body>
</html>