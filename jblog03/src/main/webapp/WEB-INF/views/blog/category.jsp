<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
	
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$("#category-insert").click(function(){
	var name = $("#name").val();
	var desc = $("#desc").val();
	var id = "${authUser.id}";
	if(name == '' || desc == ''){
		return ;
	}
	$.ajax({
		url: "${pageContext.request.contextPath }/"+id+"/admin/category",
		type: "get",
		data: {name: name, desc: desc},
		error: function(error){
			console.log("fail");
		},
		success: function(data){
			console.log("success");
		}
	});
});
</script>

</head>
<body>
	<c:import url="/WEB-INF/views/includes/blogheader.jsp" />
	<div id="container">
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blognavi.jsp" />
				<table class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<c:forEach var="category" items="${category }">
						<tr>
							<td>${category.no }</td>
							<td>${category.name }</td>
							<td></td>
							<td>${category.desc }</td>
							<td><a href="${pageContext.request.contextPath }/${authUser.id }/admin/delete/${category.no }" >
							<img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
						</tr>
					</c:forEach>
					<%-- <tr>
						<td>3</td>
						<td>미분류</td>
						<td>10</td>
						<td>카테고리를 지정하지 않은 경우</td>
						<td><img
							src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
					</tr> --%>
					
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form id="change-category" >
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" name="name" id="name"></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" name="desc" id="desc"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input id="category-insert" type="submit" value="카테고리 추가" ></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>