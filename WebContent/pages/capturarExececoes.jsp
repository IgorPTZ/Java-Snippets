<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Capturando exceções com Jquery</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<h3>Capturando exceções com Jquery</h3>
	<a href="../index.jsp">Voltar para o menu principal</a><br><br>
	<input type="text" id="txtvalor">
	<input type="button" onclick="testarExcecao();" value="Testar Exceção">
</body>

<script type="text/javascript">
	 function testarExcecao(){
		 valorInformado =  $('#txtvalor').val();
		 
		 $.ajax({
			  method: "POST",
			  url: "excecaoServlet", // Para qual servlet?
			  data: { parametro: valorInformado }
			})
			.done(function(response) {// Resposta de sucesso - nenhum erro
			    
				alert("Success: " + response);
		   })
		   .fail(function(xhr, status, errorThrown) { // Resposta de erro - algum problema ocorreu
			   
			   alert("Error: " + xhr.responseText); // Mostra resposta do servidor
		   });  
		 
	 }
</script>
</html>