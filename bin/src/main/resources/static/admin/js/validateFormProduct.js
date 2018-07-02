$(function() {
	$("#errorNameProduct").hide();
	$("#errorPrice").hide();
	$("#errorQuantity").hide();
	$("#errorDescription").hide();
	$("#errorImage").hide();

	var errorNameProduct = false;
	var errorPrice = false;
	var errorQuantity = false;
	var errorDescription = false;
	var errorImage = false;

	$("#formNameProduct").focusout(function() {
		checkNameProduct();
	});
	$("#formPrice").focusout(function() {
		checkPrice();
	});
	$("#formQuantity").focusout(function() {
		checkQuantity();
	});
	$("#formDescription").focusout(function() {
		checkDescription();
	});

	function checkNameProduct() {
		var nameProduct = $("#formNameProduct").val();
		if (nameProduct != "") {
			$("#errorNameProduct").hide();
		} else {
			$("#errorNameProduct").html("Vui lòng không để trống tên sản phẩm");
			$("#errorNameProduct").show();
			errorNameProduct = true;
		}
	}
	function checkPrice() {
		var intRegex = new RegExp(/[0-9 -()+]+$/);
		var price = $("#formPrice").val();
		if (!intRegex.test(price)) {
			$("#errorPrice").html("Vui lòng nhập đúng định dạng");
			$("#errorPrice").show();
			errorPrice = true;
		} else {
			$("#errorPrice").hide();
		}
	}
	function checkQuantity() {
		var quantity = $("#formQuantity").val();
		var pattern = new RegExp(/[0-9 -()+]+$/);

		if (pattern.test(quantity)) {
			$("#errorQuantity").hide();
		} else {
			$("#errorQuantity").html("Vui lòng nhập đúng định dạng");
			$("#errorQuantity").show();
			errorQuantity = true;
		}
	}
	function checkDescription() {
		var description = $("#formDescription").val();
		if (description == "") {
			$("#errorDescription").html("Vui lòng không được để trống mô tả");
			$("#errorDescription").show();
			errorDescription = true;
		} else {
			$("#errorDescription").hide();
		}
	}
	function checkImage() {
		var image = $("#formImage").val();
		
		if(image==null||image==""){
			$("#errorImage").html("Vui lòng chọn hình ảnh");
			$("#errorImage").show();
			errorImage = true;
		} else{
			$("#errorImage").hide();
		}
//		alert(totalAmount +"");
	}

	$("#formProduct").submit(
			function() {
				errorNameProduct = false;
				errorPrice = false;
				errorQuantity = false;
				errorDescription = false;
				errorImage = false;
				
				checkNameProduct();
				checkPrice();
				checkQuantity();
				checkDescription();
				checkImage();

				if (errorNameProduct == false && errorPrice == false
						&& errorQuantity == false && errorDescription == false && errorImage == false) {
					return true;
				} else {
					return false;
				}
			});
});












