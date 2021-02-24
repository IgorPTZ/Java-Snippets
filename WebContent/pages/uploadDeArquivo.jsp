<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Upload de arquivos</title>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<h3>Upload de arquivos</h3>
	
	<input type="file" id="file" name="file" onchange="uploadFile();">
	
	<br>
	<br>
	
	<img alt="Image" src="" id="target" width="200" height="200">
	
	<br/>
	<br/>

	<a href="uploadServlet?acao=carregar">Carregar usuarios</a>
	
	<br/>
	<br/>
	
	<table>
		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<td>${usuario.login}</td>
				<td>${usuario.senha}</td>
				<td>${usuario.nome}</td>
				<td><a href="uploadServlet?acao=baixar&id=${usuario.id}">Baixar arquivo</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<br/>
	<br/>
	<br/>
	<br/>
</body>

<script type="text/javascript">
	
	function uploadFile() {
		
		var target = document.querySelector("img");
		
		var file   = document.querySelector("input[type=file]").files[0];
		
		var reader = new FileReader();
		
		reader.onloadend = function () {
			
			target.src = reader.result;
			
			// Enviando uma imagem para o servidor atraves de uma chamada AJAX
			
			$.ajax({
				method : "POST",
				url: "uploadServlet",
				data: {fileUpload : reader.result}
			}).done(function(response){
				
				alert("Imagem armazenada com sucesso! ");
			}).fail(function(xhr, status, errorThrown){
				
				alert("Erro ao armazenar a imagem!" + xhr.responseText);
			});
		};
		
		if(file) {
			
			reader.readAsDataURL(file);
		}
		else {
			
			target.src="";
		}
	} 
</script>

</html>