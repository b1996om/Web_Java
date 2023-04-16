<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" 
    import="java.util.*, sec02.ex01.*"%>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member information</title>
<style>
	.cls1 {
		font-size:40px;
		text-align:center;
	}
	.cls2 {
		text-align:center;
	}
</style>
</head>
<body>
	<table align="center" width="100%" border="1">
		<tr align="center" bgcolor="lightblue">
			<td width="7%">${'아이디'}</td>
			<td width="7%">${'비밀번호'}</td>
			<td width="7%">${'이름'}</td>
			<td width="7%">${'이메일'}</td>
			<td width="7%">${'가입일'}</td>
		</tr>
		<c:choose>
			<c:when test="${membersList == null}"> <!-- test안에는 조건 -->
				<tr>
					<td colspan="5" width="7%">
						<b>등록된 회원이 없습니다.</b>
					</td>
				</tr>
			</c:when>
			<c:when test="${membersList != null}">
				<c:forEach var="mem" items="${membersList}"> <!--객체주소가 담긴 membersList를 "mem"에 넣겠다.-->
					<tr align="center">
						<td width="7%">${mem.id}</td> <!-- (forEach문) 회원숫자만큼 돈다. -->
						<td width="7%">${mem.pwd}</td>
						<td width="7%">${mem.name}</td>
						<td width="7%">${mem.email}</td>
						<td width="7%">${mem.joinDate}</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<a href="${contextPath}/member/memberForm.do">
		<p class="cls2">회원 가입하기</p>
	</a>
</body>
</html>