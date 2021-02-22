package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import entidade.Usuario;

public class UsuarioDao {
	
	private Connection connection;
	
	public UsuarioDao() {
		
		connection = SingleConnection.getConnection();
	}
	
	public void inserir(Usuario usuario) {
		
		try {
			
			String sql = "insert into usuario (login, senha, nome, arquivo, tipo_arquivo) values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, usuario.getLogin());
			
			preparedStatement.setString(2, usuario.getSenha());
			
			preparedStatement.setString(3, usuario.getNome());
			
			preparedStatement.setString(4, usuario.getArquivo());
			
			preparedStatement.setString(5, usuario.getTipoDoArquivo());
			
			preparedStatement.execute();
			
			connection.commit();
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
			try {
				
				connection.rollback();
			}
			catch(SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	}
	
	public List<Usuario> listar() {
		
		try {
			
			List<Usuario> usuarios = new ArrayList<Usuario>();
			
			String sql = "select * from usuario order by nome";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Usuario usuario = new Usuario(Long.parseLong(resultSet.getString("id")),
											  resultSet.getString("login"),
											  resultSet.getString("senha"),
											  resultSet.getString("nome"),
											  resultSet.getString("arquivo"),
											  resultSet.getString("tipo_arquivo"));
				
				usuarios.add(usuario);
			}
			
			return usuarios;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Usuario consultar(Long id) {
		
		try {
			
			String sql = "select * from usuario where id = " + id;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Usuario usuario = new Usuario(Long.parseLong(resultSet.getString("id")),
											  resultSet.getString("login"),
											  resultSet.getString("senha"),
											  resultSet.getString("nome"),
											  resultSet.getString("arquivo"),
											  resultSet.getString("tipo_arquivo"));
				
				return usuario;
			}
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
