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
	<%
		String userId = request.getParameter("userId");
	if(userId.length()==0) {
		/*
		RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
		dispatch.forward(request, response); 
		*/
	%>
	<jsp:forward page="login.jsp"></jsp:forward>  <!-- 액션태그(위의 코드랑 같은기능!) -->
	<%
		}
	%>
	<h1>환영합니다 <%=userId%>님!!</h1>
</body>
</html>