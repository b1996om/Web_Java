<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");  
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test ="${empty param.userId}"> <!-- empty연산자를 사용해 id값이 비었는지 체크  -->
		아이디를 입력하세요.<br>
		<a href="login.jsp">로그인창</a>
	</c:if>
	<c:if test="${not empty param.userId}"> <!-- id를 정상적으로 입력한 경우 로그인 메시지 출력 -->
		<h1>환영합니다. <c:out value="${param.userId}" />님!!</h1>
	</c:if>
</body>
</html>