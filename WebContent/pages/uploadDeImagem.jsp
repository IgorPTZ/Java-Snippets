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
	
	<input type="hidden" id="imagemBase64">
</body>

<script type="text/javascript">
	
	function uploadFile() {
		
		var target = document.querySelector("img");
		
		var file   = document.querySelector("input[type=file]").files[0];
		
		var reader = new FileReader();
		
		reader.onloadend = function () {
			
			document.getElementById("imagemBase64").value = reader.result;
		};
		
		if(file) {
			
			reader.readAsDataURL(file);
			
			// Enviando uma imagem para o servidor atraves de uma chamada AJAX
			
			$.ajax({
				method : "POST",
				url: "uploadServlet",
				data: {fileUpload : $("#imagemBase64")}
			}).done(function(response){
				
				alert("Imagem armazenada com sucesso!");
			}).fail(function(xhr, status, errorThrown){
				
				alert("Erro ao armazenar a imagem!");
			});
		}
		else {
			
			target.src="";
		}
	} 
</script>

</html>