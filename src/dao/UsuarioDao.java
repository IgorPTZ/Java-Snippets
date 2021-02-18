package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnection;

public class UsuarioDao {
	
	private Connection connection;
	
	public UsuarioDao() {
		
		connection = SingleConnection.getConnection();
	}
	
	public void inserir() {
		
		try {
			
			String sql = "insert into usuario (login, senha, nome, arquivo) values (?, ?, ?, ?)";
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
