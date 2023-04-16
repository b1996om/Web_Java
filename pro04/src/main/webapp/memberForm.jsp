<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
<style>
	body {
		margin:0 auto;
		padding:0;
		width:200px;
	}
	div {
	margin-bottom:20px;
	float:right;
	}
</style>
</head>
<body>
	<form method="post" action="member1.jsp">
		<h1>회원 가입창</h1>
		<div>아이디<input type="text" name="id"></div>
		<div>비밀번호<input type="password" name="pwd"></div>
		<div>이름<input type="text" name="name"></div>
		<div>이메일<input type="text" name="email"></div>
		 
		<input type="submit" value="가입하기">
		<input type="reset" value="다시입력">
		
		
	</form>
</body>
</html>