package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;
import entidade.Usuario;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/pages/uploadServlet")
public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UsuarioDao usuarioDao = new UsuarioDao();
       
    public UploadServlet() {
    	
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet loaded!!!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String imagem = request.getParameter("fileUpload");
			
			usuarioDao.inserir(new Usuario("admin", "teste321", "Administrador", imagem));
			
			response.getWriter().write("Upload da imagem realizado com sucesso!");
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
			response.getWriter().write("Ocorreu um erro no upload da imagem!");
		}

	}

}
