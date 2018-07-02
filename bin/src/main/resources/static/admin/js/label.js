window.onload = function() {

	// Line chart from swirlData for dashReport
	var ctx = document.getElementById("dashReport").getContext("2d");
	window.myLine = new Chart(ctx).Line(swirlData, {
		responsive : true,
		scaleShowVerticalLines : false,
		scaleBeginAtZero : true,
		multiTooltipTemplate : "<%if (label){%><%=label%>: <%}%><%= value %>",
	});

	// Pie Chart from doughutData
	var doctx = document.getElementById("chart-area3").getContext("2d");
	window.myDoughnut = new Chart(doctx).Pie(doughnutData, {
		responsive : true
	});

	// Dougnut Chart from doughnutData
	var doctx = document.getElementById("chart-area4").getContext("2d");
	window.myDoughnut = new Chart(doctx).Doughnut(doughnutData, {
		responsive : true
	});

}