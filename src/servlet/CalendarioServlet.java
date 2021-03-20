package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventosDoCalendarioDao;
import entidade.EventosDoCalendario;

@WebServlet("/pages/calendarioServlet")
public class CalendarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private EventosDoCalendarioDao eventosDoCalendarioDao = new EventosDoCalendarioDao();
       
    public CalendarioServlet() {
        
    	super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = "";
		
		List<EventosDoCalendario> eventos = eventosDoCalendarioDao.obterTodos();
		
		if(!eventos.isEmpty()) {
			
			json += "[";
			
			for(int i = 0; i < eventos.size() ; i++) {
				
				json += "{\"title\":\"" + eventos.get(i).getTitulo() + "\", \"start\": \"" + eventos.get(i).getInicio() + "\", \"end\": \"" + eventos.get(i).getFim() + "\"}";
				
				if(i != (eventos.size() - 1)) {
					
					json += ",";
				}
			}
			
			json += "]";
		}
		else {
			
			json += "[]";
		}
		
		response.setStatus(200);
		
		response.getWriter().write(json);
	}
}
