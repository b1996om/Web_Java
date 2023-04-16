<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생추가창</title>
</head>
<body>
	<h1>학생 추가</h1>
	<hr>
	<form action="${contextPath}/boad/addStudent.do" method="post">
		이름 <input type="text" name="name"><br>
		대학 <input type="text" name="univ"><br>
		생일 <input type="text" name="bitrh"><br>
		이메일 <input type="email" name="email"><br>
		<input type="submit" value="등록">
	</form>
</body>
</html>