<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");  
%>

<c:set var="id" value="hong" scope="page" />
<c:set var="pwd" value="1234" scope="page" />
<c:set var="name" value="${'홍길동'}" scope="page" />
<c:set var="age" value="${22}" scope="page" />
<c:set var="height" value="${177}" scope="page" />
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:redirect url="/test03/member1.jsp" >
		<c:param name="id" value="${'hong'}"/>
		<c:param name="pwd" value="${'1234'}"/>
		<c:param name="name" value="${'홍길동'}"/>
		<c:param name="email" value="${'hong@test.com'}"/>
	</c:redirect>
	
	<table align="center" border=1>
		<tr align="center" bgcolor="lightgreen">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>나이</b></td>
			<td width="7%"><b>키</b></td>
		</tr>
		<c:forEach var="member" items="${membersList}">
			<tr align="center">
				<td>${member.id}</td>
				<td>${member.pwd}</td>
				<td>${member.name}</td>
				<td>${member.email}</td>
				<td>${member.height}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>