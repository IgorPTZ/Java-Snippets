<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Upload de imagens</title>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<h3>Upload de imagens</h3>
	
	<input type="file" id="file" name="file" onchange="uploadFile();">
	
	<img alt="Image" src="" id="target" width="200" height="200">
	
	<a href="uploadServlet">Carregar imagens</a>
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
				
				alert("Imagem armazenada com sucesso! " + response);
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