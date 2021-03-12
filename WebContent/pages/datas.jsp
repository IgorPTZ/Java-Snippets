<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Calculando datas</title>
	<link rel="stylesheet"href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<form action="dataServlet" method="post">
		<label>Data Inicial</label>
		<input id="dataInicial" name="dataInicial">
		
		<label>Tempo em horas</label>
		<input type="text" id="tempo" name="tempo">
		
		<input type="submit" value="Calcular">
	</form>
	
	<br/>
	<br/>
	
	<label>Data Final</label>
	<input type="text" id="dataFinal" name="dataFinal" readonly="readonly" value="${dataFinal}">
</body>

<script type="text/javascript">
	$(function(){
		$('#dataInicial').datepicker({dateFormat: 'dd/mm/yy'});
	});
</script>

</html>