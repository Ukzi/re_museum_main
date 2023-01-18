function fn_submit(){
	var fn = document.frm;
	
	if(fn.c_name.value == "") {
		alert("Your name has not been entered")
		fn.c_name.focus();
		return false;
	}
	if(fn.c_birth.value == "") {
		alert("Date of birth not entered")
		fn.c_birth.focus();
		return false;
	}
	if(fn.m_no.value == "") {
		alert("Exhibition is not entered")
		fn.m_no.focus();
		return false;
	}
	if(fn.r_time.value == "") {
		alert("Reservation date not entered")
		fn.c_time.focus();
		return false;
	}
	if(fn.r_confirm.value == "") {
		alert("Payment method has not been entered")
		fn.c_confirm.focus();
		return false;
	}
	fn.submit();
}

