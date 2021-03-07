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
	
	private JRExporter exporter = null;
	
	private String caminhoSubReport_Dir = "";
	
	private File arquivoGerado = null;
	
/*	@SuppressWarnings("unchecked")
	public String gerarRelatorio0(List<?> listaDeObjetos, HashMap parametrosRelatorio,
			                     String nomeDoRelatorioJasper, String nomeDeSaida, ServletContext servletContext) throws Exception {
	
		
		// Cria a lista de elementos do relatorio
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(listaDeObjetos);
		
		// Fornece o caminho fisico para a pasta que contem os relatorios .jasper
		String caminhoDoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		File arquivo = new File(caminhoDoRelatorio + SEPARADOR + nomeDoRelatorioJasper + ".jasper");
		
		if(caminhoDoRelatorio == null
		  || (caminhoDoRelatorio != null && caminhoDoRelatorio.isEmpty())
		  || !arquivo.exists()) {
			
			caminhoDoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			
			SEPARADOR = "";
		}
		
		// Caminho completo para o relatorio compilado 
		String caminhoParaArquivosJasper = caminhoDoRelatorio + SEPARADOR + nomeDoRelatorioJasper + ".jasper";
		
		
		// Realiza o carregamento do relatorio 
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoParaArquivosJasper);
		
		
		// Preenche o parametro SUBREPORT_DIR com o caminho fisico
		caminhoDoSubReport_Dir = caminhoDoRelatorio + SEPARADOR;
		
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoDoSubReport_Dir);
		
		// Carrega o arquivo para a memoria
		JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrBeanCollectionDataSource);
			
		
		// Caminho do relatorio exportado
		exporter = new JRPdfExporter();
		
		caminhoArquivoRelatorio = caminhoDoRelatorio + SEPARADOR + nomeDeSaida + ".pdf";
		
		// Cria novo arquivo exportado
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		// Prepara a impressao 
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		// BExecuta a exportacao 
		exporter.exportReport();
		
		// Remove o arquivo do servidor apos ser feito o download
		arquivoGerado.deleteOnExit();
		
		return caminhoArquivoRelatorio;
	}   */
	
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
