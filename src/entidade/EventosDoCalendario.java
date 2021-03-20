package entidade;

public class EventosDoCalendario {
	
	private Long id;
	
	private String titulo;
	
	private String inicio;
	
	private String fim;
	
	public EventosDoCalendario(Long id, String titulo, String inicio, String fim) {
		
		this.id = id;
		
		this.titulo = titulo;
		
		this.inicio = inicio;
		
		this.fim = fim;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}
}
