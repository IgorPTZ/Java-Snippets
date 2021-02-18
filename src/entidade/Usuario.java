package entidade;

public class Usuario {
	
	private String login;
	
	private String senha;
	
	private String nome;
	
	private String arquivo;
	
	public Usuario() {
		
	}
	
	public Usuario(String login, String senha) {
		
		this.login = login;
		
		this.senha = senha;
	}
	
	public Usuario(String login, String senha, String nome, String arquivo) {
		
		this.login   = login;
		
		this.senha   = senha;
		
		this.nome    = nome;
		
		this.arquivo = arquivo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
}
