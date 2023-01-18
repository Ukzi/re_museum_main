<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Reservation"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Reservation> list = new ArrayList<Reservation>();
list = (ArrayList<Reservation>) request.getAttribute("list");
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
		<h2>Check reservation</h2>
		<div class="wrapper">
			<table style="width: 900px">
				<tr>
					<th>Name</th>
					<th>Birth</th>
					<th>Exhibit</th>
					<th>Reservation date</th>
					<th>Payment</th>
				</tr>
				<%
				for (Reservation r : list) {
				%>
				<tr>
					<td><a href="modify?r_no=<%=r.getR_no()%>"><%=r.getC_name()%></a></td>
					<td><%=r.getC_birth()%></td>
					<td><%=r.getM_no()%></td>
					<td><%=r.getR_time()%></td>
					<td><%=r.getR_confirm()%></td>
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