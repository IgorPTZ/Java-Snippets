package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/tabelaGanttServlet")
public class TabelaGanttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TabelaGanttServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = "{\"id\": \"1\", \"name\": \"Java Project\", \"series\": [" +
				"{ \"name\": \"Planned\", \"start\": new Date(2021,01,01), \"end\": new Date(2021,01,04) }," +
				"{ \"name\": \"Actual\", \"start\": new Date(2021,01,01), \"end\": new Date(2021,01,05), \"color\": \"#f0f0f0\" }," +
				"{ \"name\": \"Intended\", \"start\": new Date(2021,01,01), \"end\": new Date(2021,01,03),\"color\": \"#f0f0f0\" }" +
				"]}";
		
		/*var ganttData = [
		{
			id: 1, name: "Java Project", series: [
				{ name: "Planned", start: new Date(2021,00,01), end: new Date(2021,00,03) },
				{ name: "Actual", start: new Date(2021,00,02), end: new Date(2021,00,05), color: "#f0f0f0" },
				{ name: "Intended", start: new Date(2021,00,02), end: new Date(2021,00,05), color: "#f0f0f0" }
			]
		}
	];*/
		
		System.out.println(json);
		
		response.setStatus(200);
		
		response.getWriter().write(json);
	}
}
