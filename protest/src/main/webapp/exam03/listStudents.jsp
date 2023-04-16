<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*, exam_03.*" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 정보창</title>
</head>
<body>
	<h1>학생 정보</h1>
	<a href="${contextPath}/boad/studentForm.do">
		<p class="cls2">새로고침</p>
	</a>
	<hr>
	<table align="center" width="100%" border="1">
		<tr align="center" bgcolor="lightblue">
			<td width="7%">${'id'}</td>
			<td width="7%">${'이름'}</td>
			<td width="7%">${'대학'}</td>
			<td width="7%">${'생일'}</td>
			<td width="7%">${'이메일'}</td>
		</tr>
		<c:choose>
			<c:when test="${studentsList == null}">
				<tr>
					<td colspan="5" width="7%">
						<b>등록된 회원이 없습니다.</b>
					</td>
				</tr>
			</c:when>
			<c:when test="${studentsList != null}">
				<c:forEach var="stu" items="${studentsList}"> 
					<tr align="center">
						<td width="7%">${stu.id}</td>
						<td width="7%">${stu.name}</td>
						<td width="7%">${stu.univ}</td>
						<td width="7%">${stu.birth}</td>
						<td width="7%">${stu.email}</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<a href="${contextPath}/boad/studentForm.do">
		<p class="cls2">학생추가</p>
	</a>
</body>
</html>