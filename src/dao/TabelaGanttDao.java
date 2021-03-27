package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import entidade.Projeto;

public class TabelaGanttDao {
	
	private Connection connection;
	
	public TabelaGanttDao() {
		
		connection = SingleConnection.getConnection();
	}
	
	public List<Projeto> obterTodos() {
		
		try {
			
			List<Projeto> projetos = new ArrayList<Projeto>();
			
			String sqlUm = "select * from projeto";
			
			PreparedStatement preparedStatementUm = connection.prepareStatement(sqlUm);
			
			ResultSet resultSetUm = preparedStatementUm.executeQuery();
			
			while(resultSetUm.next()) {
				
				Projeto projeto = new Projeto(resultSetUm.getLong("id"),
											  resultSetUm.getString("nome"));
				
				projetos.add(projeto);
			}
			
			return projetos;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
