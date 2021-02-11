package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidade.Usuario;

@WebServlet("/pages/autenticacaoServlet")
public class AutenticacaoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public AutenticacaoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(Boolean.parseBoolean(request.getParameter("Sair"))) {
			
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			
			HttpSession session = servletRequest.getSession();
			
			session.invalidate();
			
			response.sendRedirect("../index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		
		String senha = request.getParameter("senha");
		
		String url   = request.getParameter("url");
		
		if(login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("teste321")) {
			
			Usuario usuarioLogado = new Usuario(login, senha);
			
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			
			HttpSession session = servletRequest.getSession();
			
			session.setAttribute("usuario", usuarioLogado);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			
			requestDispatcher.forward(request, response);
		}
		else {
			
			request.setAttribute("mensagem", "Login e/ou senha estão incorretos");
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
			
			requestDispatcher.forward(request, response);
		}
	}
}
