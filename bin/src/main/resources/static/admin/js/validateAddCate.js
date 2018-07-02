$(function() {
	$("#errorNameCategory").hide();
		
	var errorNameCategory = false;

	$("#formNameCategory").focusout(function() {
		checkNameCategory();
	});
		
	function checkNameCategory() {
		var nameCategory = $("#formNameCategory").val();
		if (nameCategory != "") {
			$("#errorNameCategory").hide();
		} else {
			$("#errorNameCategory").html("Vui lòng không để trống tên danh mục");
			$("#errorNameCategory").show();
			errorNameCategory = true;
		}
	}
		
	$("#formCategory").submit(
		function() {
			errorNameCategory = false;
				
			checkNameCategory();

			if (errorNameCategory == false) {
				return true;
			} else {
				return false;
			}
		});
});