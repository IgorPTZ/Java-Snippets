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
			
			var ganttDataResponse = JSON.parse(response);
			
			var ganttData = "";
			    
			    gattData += "[";
			
			$.each(ganttDataResponse, function(index, projeto) {
				
				gattData += "{ \"id\": \"" + projeto.id + "\", \"name\": \""+projeto.nome+"\", \"series\": [";
				
				$.each(projeto.series, function(idx, serie) {
					
					var cores = "#3366FF, #00CC00".split(',');
					
					var cor;
					
					if(idx === 0) {
						
						cor = "#CC33CC";
					}
					else {
						
						cor = Number.isInteger(idx / 2) ? cores[0] : cores[1];
					}
					
					var dataInicio = serie.dataInicio.split('-');
					
					var dataFim = serie.dataFim.split('-');
					
					ganttData += "{ \"name\": \"" + serie.nome + "\", \"start\":\"" + new Date(dataInicio[0], dataInicio[1], dataInicio[2]) + "\", \"end\": \"" + new Date(dataFim[0], dataFim[1], dataFim[2]) + "\", \"color\": \"" + cor + "\", \"projeto\":\""+ serie.projeto +"\", \"serie\":\""+ serie.id +"\"}";       
				
					if(idx < projeto.series.length - 1) {
						
						ganttData += ",";
					}
				});
				
				ganttData += "]}";
				
				if(index < ganttDataResponse.length - 1) {
					
					ganttData += ",";
				}
			});
			
			ganttData += "]";
			    
			ganttData = JSON.parse(ganttData);
			
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
						
						var start = data.start.toString("yyyy-M-d");
						
						var end = data.end.toString("yyyy-M-d");
						
						$.post("tabelaGanttServlet", {start:"", end: "", serie: data.serie, projeto: data.projeto});
						
						$("#eventMessage").text(msg);
					},
					onDrag: function (data) { 
						
						var msg = "You dragged an event: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						
						var start = data.start.toString("yyyy-M-d");
							
						var end = data.end.toString("yyyy-M-d");
						
						$.post("tabelaGanttServlet", {start: start, end: end, serie: data.serie, projeto: data.projeto});
						
						$("#eventMessage").text(msg);
					}
				}
			});	
		});
	});
</script>

</html>