<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<ul class="admin-menu">
		<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
		<li class="selected"><a href="${pageContext.request.contextPath}/${authUser.id }/admin/category">카테고리</li>
		<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/write">글작성</a></li>
	</ul>

</body>
</html>