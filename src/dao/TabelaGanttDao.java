package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import entidade.Projeto;
import entidade.Series;

public class TabelaGanttDao {
	
	private Connection connection;
	
	public TabelaGanttDao() {
		
		connection = SingleConnection.getConnection();
	}
	
	public void atualizar(Series serie) {
		
		try {
			
			String sql = "update series set data_inicial = '" + serie.getDataInicio() + ", data_final = '" + serie.getDataFim() + "'" +
			             " where id = " + serie.getId() + " and projeto = " + serie.getProjetoId();
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.executeUpdate();
			
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
	
	public List<Projeto> obterTodos() {
		
		try {
			
			List<Projeto> projetos = new ArrayList<Projeto>();
			
			String sqlProjeto = "select * from projeto";
			
			PreparedStatement preparedStatementUm = connection.prepareStatement(sqlProjeto);
			
			ResultSet resultSetProjeto = preparedStatementUm.executeQuery();
			
			while(resultSetProjeto.next()) {
						
				String sqlSeries = "select * from series where projet = " + resultSetProjeto.getLong("id");
				
				PreparedStatement preparedStatementSeries = connection.prepareStatement(sqlSeries);
				
				ResultSet resultSetSeries = preparedStatementSeries.executeQuery();
				
				List<Series> series = new ArrayList<Series>();
				
				while(resultSetSeries.next()) {
					
					Series serie = new Series(resultSetSeries.getLong("id"),
							                  resultSetSeries.getString("nome"),
							                  resultSetSeries.getString("data_inicio"),
							                  resultSetSeries.getString("data_fim"),
							                  resultSetSeries.getLong("projeto"));
					
					series.add(serie);
				}
				
				Projeto projeto = new Projeto(resultSetProjeto.getLong("id"),
						                      resultSetProjeto.getString("nome"),
						                      series);
				
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
