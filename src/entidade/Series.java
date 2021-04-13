package entidade;

public class Series {
	
	private Long id;
	
	private String nome;
	
	private String dataInicio;
	
	private String dataFim;
	
	private Long projetoId;
	
	public Series() {
		
	}
	
	public Series(Long id, String dataInicio, String dataFim, Long projetoId) {
		
		this.id = id;
		
		this.dataInicio = dataInicio;
		
		this.dataFim = dataFim;
		
		this.projetoId = projetoId;
	}
	
	public Series(Long id, String nome, String dataInicio, String dataFim, Long projetoId) {
		
		this.id = id;
		
		this.nome = nome;
		
		this.dataInicio = dataInicio;
		
		this.dataFim = dataFim;
		
		this.projetoId = projetoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public Long getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Long projetoId) {
		this.projetoId = projetoId;
	}
}
