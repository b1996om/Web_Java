<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*, sec01.ex01.*" 
    isELIgnored="false" 
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="calc" class="sec01.ex01.CalculatorBean" />
<jsp:setProperty name="calc" property="*" />
<%
   CalculatorDAO calculatorDAO = new CalculatorDAO();
   calculatorDAO.addMember(calc);
   List membersList = calculatorDAO.listMembers();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기pro06-useBean</title>
</head>
	
<body>
	<h2>계산 결과</h2>
	<table align="center" width="100%">
      <tr align="center" bgcolor="yellowgreen">
         <td width="5%">숫자</td>
         <td width="5%">연산</td>
         <td width="5%">숫자</td>
         <td width="5%">결과</td>
         </tr>
      <%
         if(membersList.size()==0) {
      %>
         <tr>
            <td colspan="5">
               <p align="center"><b><span style="font-size:9pt;">
                  등록된 회원이 없습니다.</span></b></p>
            </td>
         </tr>
      <%
         } else{
            for(int i=0; i<membersList.size(); i++) {
               CalculatorBean bean = (CalculatorBean) membersList.get(i);   
      %>
      <tr align="center">
         <td><%= bean.getN1() %></td> 
         <td><%= bean.getOp() %></td>
         <td><%= bean.getN2() %></td>
         <td><%= bean.getResult() %></td>
      </tr>
      <%
            }   //end for
         }   //end if
      %>
         <tr height="1" bgcolor="yellowgreen">
            <td colspan="5"></td>
         </tr>
   </table>   
 
	<!-- 결과 : <%=calc.calc() %>  -->
</body>
</html>