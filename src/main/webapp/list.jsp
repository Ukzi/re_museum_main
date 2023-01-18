<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.*" %>
<%@ page import="DTO.Museum"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Museum> list= new ArrayList<Museum>();
list = (ArrayList<Museum>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet">
</head>
<body>
	 <%@ include file="topmenu.jsp"%>
	  <section>
      <div class="title">Exhibition check</div>
      <div class="wrapper">
         <table style="width:900px">
            <tr>
               <th></th>
               <th>Exhibition name</th>
               <th>Phone number</th>
            </tr>
            <%
            for (Museum m : list) {
            %>
            <tr>
               <td><%=m.getM_no() %></td>
               <td><%=m.getM_name() %></td>
               <td><%=m.getM_tel() %></td>
            </tr>
            <%
            }
            %>
         </table>
      </div>   
   </section>
   <%@ include file="footer.jsp"%>
</body>
</html>