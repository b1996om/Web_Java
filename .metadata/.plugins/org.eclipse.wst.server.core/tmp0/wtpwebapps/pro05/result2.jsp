<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%
	request.setCharacterEncoding("utf-8");
%>   
<%!
	String msg = "아이디를 입력하지 않았습니다. 아이디를 입력해주세요";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과창</title>
</head>
<body>
	<%
		String userId = request.getParameter("userId");
		if(userId.length()==0) {
	%>
		<jsp:forward page="login2.jsp">
		<jsp:param name="msg" value="<%=msg %>" />
		</jsp:forward>
		
	<%
		}
	%>
	<h1>환영합니다. <%=userId  %>님!!!</h1>
</body>
</html>