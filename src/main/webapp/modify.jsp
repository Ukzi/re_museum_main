<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Reservation"%>
<%
request.setCharacterEncoding("UTF-8");
Reservation reservation = new Reservation();
reservation = (Reservation) request.getAttribute("reservation");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="script.js"></script>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="topmenu.jsp"%>
	<section>
		<div class="title">예약정보수정</div>
		<form name="frm" action="update">
			<input type="hidden" name="r_no" value="<%=reservation.getR_no()%>">
			<div class="wrapper">
			<div class="delete"><a href="delete?r_no=<%=reservation.getR_no()%>">Delete</a></div>
				<table>
					<tr>
						<th>Name</th>
						<td><input type="text" name="c_name" 
					     	value="<%=reservation.getC_name()%>"></td>
					</tr>
						<th>Birth</th>
						<td><input type="text" name="c_birth"
							value="<%=reservation.getC_birth()%>"></td>
					</tr>
					<tr>
						<th>Exhibit</th>
						<td>
							<select name = "m_no">
								<option value="1">WATERFALL</option>
								<option value="2">FLOWER</option>
								<option value="3">BEACH</option>
								<option value="4">GARDEN</option>
								<option value="5">STAR</option>
								<option value="6">THUNDER</option>
								<option value="7">JUNGLE</option>
								<option value="8">WHALE</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>Reservation date	</th>
						<td><input type="date" name="r_time"
							value="<%=reservation.getR_time()%>"></td>
					</tr>
					<tr>
						<th>Payment</th>
							<td class="sv">
						<label>On-site</label>
							<input type = "radio" name = "r_confirm" value="Y">
						<label>Make a deposit</label>
							<input type = "radio" name = "r_confirm" value="N">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn" type="submit"
								onclick="fn_submit(); return false;">Modify</button>
							<button class="btn" type="button" onclick="location='customerlist'">List
							</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>