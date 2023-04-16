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
	<c:choose>
		<c:when test='${msg=="addMember"}'>
			<script type="text/javascript">
				window.onload = function() {
					alert("회원을 등록했습니다.")
				}
			</script>
		</c:when>
		<c:when test='${msg=="modified"}'>
			<script type="text/javascript">
				window.onload = function() {
					alert("회원을 수정했습니다.")
				}
			</script>
		</c:when>
		<c:when test='${msg=="deleted"}'>
			<script type="text/javascript">
				window.onload = function() {
					alert("회원을 삭제했습니다.")
				}
			</script>
		</c:when>
	</c:choose>
<body>
	<table align="center" width="100%" border="1">
		<tr align="center" bgcolor="lightgreen">
			<td width="7%">ID</td>
			<td width="7%">PWD</td>
			<td width="7%">NAME</td>
			<td width="7%">EMAIL</td>
			<td width="7%">JOINDATE</td>
			<td width="7%">수정</td>
			<td width="7%">삭제</td>
		</tr>
		
		<c:choose>
			<c:when test="${membersList == null}">
				<tr align="center">
					<td colspan="7" width="7%">
						<b>등록된 회원이 없습니다.</b>
					</td>
				</tr> 
			</c:when>
			<c:when test="${membersList != null}">
				<c:forEach var="mem" items="${membersList}">
					<tr align="center">
						<td widht="7%">${mem.id}</td>
						<td widht="7%">${mem.pwd}</td>
						<td widht="7%">${mem.name}</td>
						<td widht="7%">${mem.email}</td>
						<td widht="7%">${mem.joinDate}</td>
						<td widht="7%">
							<a href="${contextPath}/member152/modMemberForm.do?id=${mem.id}">수정</a>
						</td>
						<td widht="7%">
							<a href="${contextPath}/member152/delMember.do?id=${mem.id}">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>	
	<a href="${contextPath}/member152/memberForm.do"><p calss="cls2">회원가입하기</p></a>
</body>
</html>