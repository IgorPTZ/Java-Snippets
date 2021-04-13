package servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.TabelaGanttDao;
import entidade.Projeto;
import entidade.Series;

@WebServlet("/pages/tabelaGanttServlet")
public class TabelaGanttServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private TabelaGanttDao tabelaGanttDao = new TabelaGanttDao();
	
    public TabelaGanttServlet() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    	
    	Series serie = new Series();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
		
		try {
			
			List<Projeto> projetos = tabelaGanttDao.obterTodos();
			
			if(!projetos.isEmpty()) {
				
				String json = new Gson().toJson(projetos);
				
				response.setStatus(200);
				
				response.getWriter().write(json);
			}
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
