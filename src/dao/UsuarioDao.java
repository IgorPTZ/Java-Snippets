package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnection;
import entidade.Usuario;

public class UsuarioDao {
	
	private Connection connection;
	
	public UsuarioDao() {
		
		connection = SingleConnection.getConnection();
	}
	
	public void inserir(Usuario usuario) {
		
		try {
			
			String sql = "insert into usuario (login, senha, nome, arquivo) values (?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, usuario.getLogin());
			
			preparedStatement.setString(2, usuario.getSenha());
			
			preparedStatement.setString(3, usuario.getNome());
			
			preparedStatement.setString(4, usuario.getArquivo());
			
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
}
