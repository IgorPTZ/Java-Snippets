package filtro;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnection;
import entidade.Usuario;


// Intercepta todas as requisições de todas as URL's
@WebFilter(urlPatterns={"/pages/*"})
public class FiltroAutenticacao implements Filter{
	
	private static Connection connection;

	// Executado quando a aplicação é terminada
	@Override
	public void destroy() {
		
	}

	// Intercepta todas as requisições
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {		
		
		/* Verifica se o usuario foi autenticado */
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		
		HttpSession session = servletRequest.getSession();
		
		String urlParaAutenticar = servletRequest.getServletPath();
		
		// Retorna null caso nao esteja logado
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
		
		if(usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/pages/autenticacaoServlet")) {
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp?url=" + urlParaAutenticar);
			
			requestDispatcher.forward(request, response);
			
			return;
		}
		
		chain.doFilter(request, response);	
	}

	
	// Executado quando a aplicação é iniciada
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		connection = SingleConnection.getConnection();
	}
}
