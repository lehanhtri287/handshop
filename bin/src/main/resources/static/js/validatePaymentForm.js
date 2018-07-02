$(function() {
	$("#errorName").hide();
	$("#errorEmail").hide();
	$("#errorPhone").hide();
	$("#errorAddress").hide();
	$("#errorListProduct").hide();

	var errorEmail = false;
	var errorName = false;
	var errorPhone = false;
	var errorAddress = false;
	var errorCart = false;

	$("#formEmail").focusout(function() {
		checkEmail();
	});
	$("#formName").focusout(function() {
		checkName();
	});
	$("#formPhone").focusout(function() {
		checkPhone();
	});
	$("#formAddress").focusout(function() {
		checkAddress();
	});

	function checkEmail() {
		var pattern = new RegExp(
				/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);

		if (pattern.test($("#formEmail").val())) {
			$("#errorEmail").hide();
		} else {
			$("#errorEmail").html("Vui lòng nhập đúng địa chỉ email");
			$("#errorEmail").show();
			errorEmail = true;
		}
	}
	function checkName() {
		var name = $("#formName").val();
		if (name == "") {
			$("#errorName").html("Vui lòng không được để trống tên");
			$("#errorName").show();
			errorName = true;
		} else {
			$("#errorName").hide();
		}
	}
	function checkPhone() {
		var phone = $("#formPhone").val();
		var pattern = new RegExp(
				/\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/);

		if (pattern.test(phone)) {
			$("#errorPhone").hide();
		} else {
			$("#errorPhone").html("Vui lòng nhập đúng số điện thoại");
			$("#errorPhone").show();
			errorEmail = true;
		}
	}
	function checkAddress() {
		var address = $("#formAddress").val();
		if (address == "") {
			$("#errorAddress").html("Vui lòng không được để trống địa chỉ");
			$("#errorAddress").show();
			errorAddress = true;
		} else {
			$("#errorAddress").hide();
		}
	}
	function checkListProducts() {
		var totalAmount = $("#totalAmount").html();
		
		if(totalAmount == "000"){
			$("#errorListProducts").html("Giỏ hàng đang trống")
			$("#errorListProducts").show();
			errorCart = true;
		} else{
			$("#errorListProducts").hide();
		}
//		alert(totalAmount +"");
	}

	$("#formPayment").submit(
			function() {
				errorEmail = false;
				errorName = false;
				errorPhone = false;
				errorAddress = false;
				errorCart = false;
				
				checkName();
				checkEmail();
				checkPhone();
				checkAddress();
				checkListProducts();

				if (errorEmail == false && errorName == false
						&& errorPhone == false && errorAddress == false && errorCart == false) {
					return true;
				} else {
					return false;
				}
			});
});
