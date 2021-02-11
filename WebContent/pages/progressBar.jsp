<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	
	<title>Barra de progresso</title>
	
	<style type="text/css">
		/* Fundo da barra de progresso */
		#progresso {
			width: 100%;
			background-color: #ddd; 
		}
		
		/* Barra de progresso */
		#barra {
			width: 0.5%;
			height: 30px;
			background-color: #4CAF50;
		}
	</style>
</head>
<body>
	<h3>Barra de progresso - Exemplo com Javascript</h3>
	
	<a href="../index.jsp">Voltar para o menu principal</a><br><br>
	
	<div id="progresso"> <!-- Fundo da barra de progresso -->
		<div id="barra"></div> <!-- Barra de progresso -->
	</div>
	
	<br/>
	
	<button onclick="exibirBarraDeProgresso()">Iniciar</button>
	
	<script type="text/javascript">
		
		function exibirBarraDeProgresso() {
			
			var elemento = document.getElementById("barra");
			
			var largura = 1;
			
			var id = setInterval(inserirFrame, 10);
			
			function inserirFrame() {
				
				if(largura >= 100) {
					
					clearInterval(id);
				}
				else {
					
					largura++;
					elemento.style.width = largura + "%";
				}
			}
		}
	</script>
</body>
</html>