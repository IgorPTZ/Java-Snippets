package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnection;

public class HorariosDao {
	
	private Connection connection;
	
	public HorariosDao() {
		
		connection = SingleConnection.getConnection();
	}
	
	public void inserir(String data) {
		
		try {
			
			String sql = "insert into horarios (dataFinal) values (?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, data);
			
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
