<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Tabela Gantt</title>
	<link rel="stylesheet" type="text/css" href="../script-gantt/jquery-ui-1.8.4.css" />
	<link rel="stylesheet" type="text/css" href="../script-gantt/reset.css" />
	<link rel="stylesheet" type="text/css" href="../script-gantt/jquery.ganttView.css" />
	
	<script type="text/javascript" src="../script-gantt/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="../script-gantt/date.js"></script>
	<script type="text/javascript" src="../script-gantt/jquery-ui-1.8.4.js"></script>
	<script type="text/javascript" src="../script-gantt/jquery.ganttView.js"></script>
	
	<style type="text/css">
		body {
			font-family: tahoma, verdana, helvetica;
			font-size: 0.8em;
			padding: 10px;
		}
	</style>
</head>
<body>
	<h3>Tabela Gantt</h3>
	
	<a href="../index.jsp">Voltar para o menu principal</a><br><br>
	
	<div id="ganttChart"></div>
	
	<br/><br/>
	
	<div id="eventMessage"></div>
</body>

<script type="text/javascript">

	$(document).ready(function() {
		
		$.get("tabelaGanttServlet", function(response) {
			
			var ganttData = JSON.parse(response);
			
			/*var ganttData = [
				{
					id: 1, name: "Java Project", series: [
						{ name: "Planned", start: new Date(2021,01,01), end: new Date(2021,01,03) },
						{ name: "Actual", start: new Date(2021,01,10), end: new Date(2021,00,12), color: "#f0f0f0" },
						{ name: "Intended", start: new Date(2021,01,20), end: new Date(2021,01,25), color: "#f0f0f0" }
					]
				}
			];*/
			
			$("#ganttChart").ganttView({ 
				data: ganttData,
				slideWidth: 200,
				behavior: {
					onClick: function (data) { 
						var msg = "You clicked on an event: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					},
					onResize: function (data) { 
						var msg = "You resized an event: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					},
					onDrag: function (data) { 
						var msg = "You dragged an event: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					}
				}
			});	
		});
	});
</script>

</html>