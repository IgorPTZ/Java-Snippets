<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Calendário em JQuery</title>
	<link href="../script/fullcalendar.min.css" rel="stylesheet" />
	<link href="../script/fullcalendar.print.min.css" rel="stylesheet" media="print"/>
	<script src="../script/moment.min.js"></script>
	<script src="../script/jquery.min.js"></script>
	<script src="../script/fullcalendar.min.js"></script>
	<style>
		body {
			margin: 40px 10px;
			padding: 0;
			font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
			font-size: 14px;
		}
	
		#calendar {
			max-width: 900px;
			margin: 0 auto;
		}
	</style>
</head>
<body>
	<h3>Calendário em JQuery</h3>
	
	<a href="../index.jsp">Voltar para o menu principal</a><br><br>
	
	<div id="calendar"></div>
</body>

<script>

	$(document).ready(function() {
		
		$.get("calendarioServlet", function(data) {
			
			alert(data);
			
		/* 	var dates = {
					title: 'Long Event',
					start: '2017-02-07',
					end: '2017-02-10'
				}; */
			
			$('#calendar').fullCalendar({
				header: {
					left: 'prev,next today',
					center: 'title',
					right: 'month,basicWeek,basicDay'
				},
				defaultDate: '2017-02-12',
				navLinks: true, // can click day/week names to navigate views
				editable: true,
				eventLimit: true, // allow "more" link when too many events
				events: [data]
			});
		});	
	});

</script>

</html>