<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	
	<title>Pagina pai</title>
	
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<h3>Pagina pai</h3>
	
	<a href="../index.jsp">Voltar para o menu principal</a><br><br>
	
	<input type="button" value="carregar pagina" onclick="carregarPagina();">
	
	<div id="divDaPaginaFilho"></div>
</body>

<script type="text/javascript">
	function carregarPagina() {
		
		$("#divDaPaginaFilho").load('paginaFilho.jsp'); // Carrega pagina filho na div acima atraves de chamada Ajax
	}
</script>

</html>