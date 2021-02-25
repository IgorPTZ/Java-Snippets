package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/pages/usuarioServlet")
public class UsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public UsuarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsonDaResposta = "{" + 
				"\"draw\": 1," + 
				"\"recordsTotal\": 2," + 
				"\"recordsFiltered\": 2," + 
				"\"data\": [[" + 
				" \"Airi Satou\"," + 
				"\"airi.satou\"," + 
				"\"test321\"" + 
				"]," + 
				"[" + 
				"\"Izzi Stevens\"," + 
				"\"izzi.stevens\"," + 
				"\"test123\"" + 
				"]]" + 
				"}";
		
 		System.out.println(jsonDaResposta);
		
		response.setStatus(200);
		
		response.getWriter().write(jsonDaResposta); // Retorno do JSON para o client
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
