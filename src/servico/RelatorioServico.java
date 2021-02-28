package servico;

import java.io.File;
import java.io.Serializable;

import net.sf.jasperreports.engine.JRExporter;

public class RelatorioServico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String DIRETORIO_RELATORIOS = "/relatorios";
	
	private static final String DIRETORIO_SUB_RELATORIO = "SUBREPORT_DIR";
	
	private static final String SEPARADOR = File.separator;
	
	private static final String CAMINHO_DO_RELATORIO = null;
	
	private JRExporter exporter = null;
	
	private String caminhoDoSubRelatorio = "";
	
	private File arquivoGerado = null;
}
