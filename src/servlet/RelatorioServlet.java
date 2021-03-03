package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;
import entidade.Usuario;
import service.RelatorioService;

@WebServlet("/relatorioServlet")
public class RelatorioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UsuarioDao usuarioDao = new UsuarioDao();
	
	private RelatorioService relatorioService = new RelatorioService();
       
    public RelatorioServlet() {
       
    	super();
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String extensaoDoRelatorio = request.getParameter("extensaoDoRelatorio");
			
			List<Usuario> usuarios = usuarioDao.obterTodos();
			
			List dadosDoRelatorio = new ArrayList();
			
			dadosDoRelatorio.add(usuarios);
			
			String urlDoArquivo = relatorioService.gerarRelatorio(dadosDoRelatorio, 
					                                              new HashMap(), 
					                                              "rel_usuario", 
					                                              "rel_usuario", 
					                                              request.getServletContext());
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
