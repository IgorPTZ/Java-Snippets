<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	
	<title>Barra de progresso</title>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
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
		
		.ui-progressbar {
			position: relative
		}
		
		.progress-label {
			position: relative;
			left: 50%;
			top: 4px;
			font-weight: bold;
			text-shadow: 1px 1px 0 #fff;
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
	
	
	<br/>
	<h3>Barra de progresso - Exemplo com JQuery</h3>
	<div id="progressbar">
		<div class="progress-label">
			Carregando...
		</div>
	</div>
	
	
	
	

	<script type="text/javascript">
		
		// Script da barra de progresso feita com JQuery
		$(function () {
			
			var progressbar = $("#progressbar");
			
			var progresslabel = $(".progress-label");
			
			progressbar.progressbar ({ // Cria a barra de progresso na div
				value : false,
				change : function () {
					progresslabel.text(progressbar.progressbar('value') + "%")
				},
				complete: function () {
					progresslabel.text('Completo!')
				}
			});
			
			
			function progress() {
				
				var value = progressbar.progressbar("value") || 0;
				
				progressbar.progressbar("value", value + 2);
				
				if(value < 99) {
					setTimeout(progress, 80);
				}
 			}
			
			setTimeout(progress, 2000);	// Chamado assim que a tela abre
		});
		
	
		// Script da barra de progresso feita com Javascript
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