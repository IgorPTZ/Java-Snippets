package execoes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExcecaoServlet
 */
@WebServlet("/pages/excecaoServlet")
public class ExcecaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExcecaoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String valor = request.getParameter("parametro");
			
			Integer.parseInt(valor);
			
			System.out.println(request.getParameter("parametro"));
			
			response.setStatus(200);
			
			response.getWriter().write("Processado com sucesso!");
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
			response.setStatus(500);
			
			response.getWriter().write("Ocorreu um erro no servidor! " + e.getMessage());
		}	
	}
}
