<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página de login</title>
</head>
<body>
	<h3>Página de login</h3>
	<h4 style="color: red;">${mensagem}</h4>
	<a href="../index.jsp">Voltar para o menu principal</a>
	<form action="autenticacaoServlet" method="post">
	
		<input type="hidden" readonly="readonly" id="url" name="url" value="<%= request.getParameter("url") %>">
		<table>
			<tr>
				<td>Login:</td>
				<td><input type="text" id="login" name="login"></td>
			</tr>
			
			<tr>
				<td>Senha:</td>
				<td><input type="password" id="senha" name="senha"></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Acessar"></td>	
			</tr>		
		</table>
	</form>
</body>
</html>