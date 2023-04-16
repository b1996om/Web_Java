<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
 	<form method="post" action="result.jsp">
 		<input type="hidden" name="param1" value="aa.jpg" /><br>
 		<input type="hidden" name="param2" value="bb.jpg" /><br>
 		<input type="submit" value="이미지 다운로드">
 	</form>
</body>
</html>