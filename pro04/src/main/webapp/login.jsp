<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<h1>아이디를 입력하지 않았습니다. 아이디를 입력해주세요.</h1>
	<form action="result.jsp" method="post">
		아이디:<input type="text" name="userId">
		비밀번호:<input type="password" name="userPw">
		<input type="submit" value="로그인">
		<input type="reset" value="다시입력">
	</form>
</body>
</html>