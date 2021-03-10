package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String FOLDER_RELATORIOS = "/relatorios";
	
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	
	private String SEPARATOR = File.separator;  
	
	private String caminhoArquivoRelatorio = null;
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	private JRExporter exporter = null;
	
	private String caminhoSubReport_Dir = "";
	
	private File arquivoGerado = null;
	
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public String gerarRelatorio(List<?> listaDataBeanColletion, HashMap parametrosRelatorio
			, String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext) throws Exception{

		
		/* Cria a lista de elementos do relatorio */
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanColletion);
		
		/* Fornece o caminho fisico para a pasta que contem os relatorios .jasper */
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");
		
		if (caminhoRelatorio == null
				|| (caminhoRelatorio != null && caminhoRelatorio.isEmpty())
				|| !file.exists()){
			
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			
			SEPARATOR = "";
		}
		
		/*caminho para imagens*/
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		/* Caminho completo para o relatorio compilado */
		String caminhoArquivosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
		
		/* Realiza o carregamento do relatorio */
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);
		
		/* Preenche o parametro SUBREPORT_DIR com o caminho fisico */
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
		
		parametrosRelatorio.put(SUBREPORT_DIR,caminhoSubReport_Dir);
		
		/* Carrega o arquivo para a memoria */
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		/* Caminho do relatorio exportado */
		exporter = new JRPdfExporter();
	
		caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + ".pdf";
		
		/*Criar novo arquivos exportado*/
		
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		/* Prepara a impressao */
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		/*Excuta a exportação*/
		exporter.exportReport();
		
		/*Remove o arquivo do servidor após ser feito o download*/
		arquivoGerado.deleteOnExit();
		
		return caminhoArquivoRelatorio;
	}
}
