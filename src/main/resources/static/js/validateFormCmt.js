$(function() {
	$("#errorName").hide();
	$("#errorEmail").hide();
	$("#errorContext").hide();
	
	var errorName = false;
	var errorEmail = false;
	var errorContext = false;
	
	$("#formEmail").focusout(function() {
		checkEmail();
	});
	$("#formName").focusout(function() {
		checkName();
	});
	$("#formContext").focusout(function() {
		checkContext();
	});
	
	function checkEmail() {
		var email = $("#formEmail").val();
		if (email == "") {
			$("#errorEmail").html("Vui lòng đăng nhập để nhận xét");
			$("#errorEmail").show();
			errorEmail = true;
		} else {
			$("#errorEmail").hide();
		}
	}
	function checkName() {
		var name = $("#formName").val();
		if (name == "") {
			$("#errorName").html("Vui lòng đăng nhập để nhận xét");
			$("#errorName").show();
			errorName = true;
		} else {
			$("#errorName").hide();
		}
	}
	function checkContext() {
		var context = $("#formContext").val();
		if (context == "") {
			$("#errorContext").html("Vui lòng không để trống nội dung");
			$("#errorContext").show();
			errorContext = true;
		} else {
			$("#errorContext").hide();
		}	
	}
	
	$("#formComment").submit(
		function() {
			errorEmail = false;
			errorName = false;
			errorContext = false;
			
			checkName();
			checkEmail();
			checkContext();
			
			if (errorEmail == false && errorName == false && errorContext == false) {
				return true;
			} else {
				return false;
			}
		}
	);
});
