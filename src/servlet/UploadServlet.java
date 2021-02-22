package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

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

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			
			if(acao.equalsIgnoreCase("carregar")) {
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("uploadDeImagem.jsp");
				
				request.setAttribute("usuarios", usuarioDao.listar());
				
				requestDispatcher.forward(request, response);
			}
			else if(acao.equalsIgnoreCase("baixar")) {
				
				Long usuarioId = Long.parseLong(request.getParameter("id"));
				
				Usuario usuario = usuarioDao.consultar(usuarioId);
				
				if(usuario.getArquivo() != null) {
					
					response.setHeader("Content-Disposition", "attachment;filename=imagem.png");
					
					/* Obtem o base64 do arquivo */
					String arquivoBase64 = usuario.getArquivo().split(",")[1];
					
					/* Converte o base64 em bytes */
					byte[] arquivoEmBytes = new Base64().decodeBase64(arquivoBase64);
					
					/* Insere os bytes em um objeto de entrada */
					InputStream inputStream = new ByteArrayInputStream(arquivoEmBytes);
					
					/* INICIO - Escrita de dados na resposta */
					
					int leitura = 0;
					
					byte[] bytes = new byte[1024];
					
					OutputStream outputStream = response.getOutputStream();
					
					while((leitura = inputStream.read(bytes)) != -1) {
						
						outputStream.write(bytes, 0, leitura);
					}
					
					outputStream.flush();
					
					outputStream.close();
					
					/* FIM - Escrita de dados na resposta */
				}
			}
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
			response.getWriter().write("Ocorreu um erro ao carregar os usuarios!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String imagem = request.getParameter("fileUpload");
			
			usuarioDao.inserir(new Usuario(null, "admin", "teste321", "Administrador", imagem, null));
			
			response.getWriter().write("Upload da imagem realizado com sucesso!");
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
			response.getWriter().write("Ocorreu um erro no upload da imagem!");
		}

	}
}
