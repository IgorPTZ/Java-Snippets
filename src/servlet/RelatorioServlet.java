package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;
import entidade.Usuario;
import service.RelatorioService;

@WebServlet("/pages/relatorioServlet")
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
			ServletContext servletContext = request.getServletContext();
			
			String extensaoDoRelatorio = request.getParameter("extensaoDoRelatorio");
			
			List<Usuario> usuarios = usuarioDao.obterTodos();
			
			List dadosDoRelatorio = new ArrayList();
			
			dadosDoRelatorio.add(usuarios);
			
			String urlDoArquivo = relatorioService.gerarRelatorio(dadosDoRelatorio, 
					                                              new HashMap(), 
					                                              "rel_usuario", 
					                                              "rel_usuario", 
					                                              servletContext);
			
			
			// Controi o caminho completo e absoluto do arquivo
			File downloadFile = new File(urlDoArquivo);
			
			FileInputStream inputStream = new FileInputStream(downloadFile);
			
			// Obtem o tipo MIME do arquivo
			String mimeType = servletContext.getMimeType(urlDoArquivo);
			
			if(mimeType == null) {
				
				// Define o tipo como binario se o mapeamento do mime nao for encontrado
				mimeType = "application/octet-stream";
			}
			
			// Define os atributos do response
			response.setContentType(mimeType);
			
			response.setContentLength((int) downloadFile.length());
			
			// Define os headers do response	
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", downloadFile.getName()));
			
			// Obtem o fluxo de saida da resposta
			OutputStream outputStream = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			
			int bytesReader = -1;
			
			// Escreve bytes lider do fluxo de entrada para o fluxo de saida
			while((bytesReader = inputStream.read(buffer)) != -1) {
				
				outputStream.write(buffer, 0, bytesReader);
			}
			
			inputStream.close();
			
			outputStream.close();
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
