package servico;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioServico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String FOLDER_RELATORIOS = "/relatorios";
	
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	
	private static String SEPARADOR = File.separator;
	
	private static final String caminhoArquivoRelatorio = null;
	
	private JRExporter exporter = null;
	
	private String caminhoDoSubReport_Dir = "";
	
	private File arquivoGerado = null;
	
	
	@SuppressWarnings("unchecked")
	public String gerarRelatorio(List<?> listaDeObjetos, HashMap parametrosRelatorio,
			                     String nomeDoRelatorioJasper, String nomeDeSaida, ServletContext servletContext) throws Exception {
	
		
		/* Cria a lista de elementos do relatorio */
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(listaDeObjetos);
		
		/* Fornece o caminho fisico para a pasta que contem os relatorios .jasper */
		String caminhoDoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		File arquivo = new File(caminhoDoRelatorio + SEPARADOR + nomeDoRelatorioJasper + ".jasper");
		
		if(caminhoDoRelatorio == null
		  || (caminhoDoRelatorio != null && caminhoDoRelatorio.isEmpty())
		  || !arquivo.exists()) {
			
			caminhoDoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			
			SEPARADOR = "";
		}
		
		/* Caminho completo para o relatorio compilado */
		String caminhoParaArquivosJasper = caminhoDoRelatorio + SEPARADOR + nomeDoRelatorioJasper + ".jasper";
		
		
		/* Realiza o carregamento do relatorio */
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoParaArquivosJasper);
		
		
		/* Preenche o parametro SUBREPORT_DIR com o caminho fisico */
		caminhoDoSubReport_Dir = caminhoDoRelatorio + SEPARADOR;
		
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoDoSubReport_Dir);
		
		/* Carrega o arquivo para a memoria */
		JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrBeanCollectionDataSource);
				
		return "";
	}
}
