<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	
	<title>DataTable em JQuery</title>
	
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.23/datatables.min.css"/>
 	
 	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
 	
	<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.23/datatables.min.js"></script>
</head>
<body>
	<h3>DataTable em JQuery</h3>

	<table id="tabelaDeUsuarios" class="display" style="width:100%">
        <thead>
            <tr>
            	<th>Id</th>
                <th>Nome</th>
                <th>Login</th>
                <th>Senha</th>
            </tr>
        </thead>
    </table>
</body>

<script type="text/javascript">

	$(document).ready(function() {
	    $('#tabelaDeUsuarios').DataTable({
	    	"processing": true,
	    	"serverSide": true,
	    	"ajax": "usuarioServlet" // Retorno da chamada ao servidor
	    });
	} );
</script>
</html>