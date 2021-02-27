package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;
import entidade.Usuario;


@WebServlet("/pages/usuarioServlet")
public class UsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UsuarioDao usuarioDao = new UsuarioDao();
       
    public UsuarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String jsonDaResposta = "";
			
			List<Usuario> usuarios = usuarioDao.listar();
			
			if(!usuarios.isEmpty()) {
				
				String dadosDosUsuarios = "";
				
				int index = 1;
				
				int totalDeUsuarios = usuarios.size();
				
				for(Usuario usuario : usuarios) {
					
					
					dadosDosUsuarios += "[\"" + usuario.getId() + "\"," + 
							            "\"" + usuario.getNome() + "\"," +
							            "\"" + usuario.getLogin() + "\"," +
							            "\"" + usuario.getSenha() + "\"]";
					
					if(index < totalDeUsuarios) {
						
						dadosDosUsuarios += ",";
					}
					
					index++;
				}
				
				jsonDaResposta = "{" + 
						"\"draw\": 1," + 
						"\"recordsTotal\":" + usuarios.size() + "," + 
						"\"recordsFiltered\":" + usuarios.size() + "," + 
						"\"data\": [" + dadosDosUsuarios + "]" + 
						"}";
				

			}
			else {
				
				jsonDaResposta = "{" + 
						"\"draw\": 1," + 
						"\"recordsTotal\": 0," + 
						"\"recordsFiltered\": 0," + 
						"\"data\": []" + 
						"}";
			}
			
	 		System.out.println(jsonDaResposta);
			
			response.setStatus(200);
			
			response.getWriter().write(jsonDaResposta); // Retorno do JSON para o client
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			response.setStatus(500);
		}
	}
}
