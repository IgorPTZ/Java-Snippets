package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/calendarioServlet")
public class CalendarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public CalendarioServlet() {
        
    	super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ServletCalendario called sucessfully!");
		
		String eventos = "{title: 'Long Event', start: '2017-02-07', end: '2017-02-10'}";
		
		response.setStatus(200);
		
		response.getWriter().write(eventos);
	}
}
