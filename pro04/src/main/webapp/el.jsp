<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored = "false"%>
    
    <jsp:useBean id="gil" class="sec01.ex04.test" />
    <jsp:setProperty name="gil" property="*" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>
	${10+20} <!-- 사칙연산, 30 -->
	${10*20} <!-- 사칙연산, 200 -->
	${true && false} <!-- 논리연산, false  -->
	${10 >= 20} <!-- 논리연산, false  -->
	
	 <form method="get" action="sec01/ex04/test.java" >
		이름 : <input type="text" name="name"></input>
		<input type="submit" value="check">
	</form> 
	
	
	
	${user.name == "홍길동"? "교수": "학생"} <!-- 3항연산 -->
	${gil.test(name)}
	
	
	<%-- 
		${myList[0]} -- 배열인 경우
		${myMap["name"]} -- 맵인 경우
	--%>
</body>
</html>