<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DTO.Customer" %>
<%@ page import="DTO.Museum" %>
<%@ page import="DTO.Reservation" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet">
<script type="text/javascript" src = "script.js"></script>
</head>
<body>
 <%@ include file="topmenu.jsp" %>
   <section>
		<div class="title">Reservation</div>
		<form name="frm" action="insert" method="post">
			<input type = "hidden" id="GUBUN" value = "insert">
			<div class = "wrapper">
				<table>
					<tr>
						<th>Name</th>
						<td><input type = "text" name ="c_name"></td>
					</tr>
					<tr>
						<th>Birth</th>
						<td><input type = "text" name ="c_birth"> ex) 890615</td>
					</tr>
					<tr>
						<th>Exhibit</th>
						<td>
							<select name = "m_no">
								<option></option>
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
						<th>Reservation date</th>
						<td><input type = "date" name = "r_time"></td>
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
							<button class = "btn" type = "submit" onclick="fn_submit(); return false;">Reservation</button>
							<button class = "btn" type = "button">Reset</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</section>
	   <%@ include file="footer.jsp" %>
</body>
</html>