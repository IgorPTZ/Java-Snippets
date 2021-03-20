package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import entidade.EventosDoCalendario;

public class EventosDoCalendarioDao {
	
	private Connection connection;
	
	public EventosDoCalendarioDao () {
		
		connection = SingleConnection.getConnection();
	}
	
	public List<EventosDoCalendario> obterTodos() {
		
		try {
			
			List<EventosDoCalendario> eventos = new ArrayList<EventosDoCalendario>();
			
			String sql = "select * from eventos_do_calendario order by titulo";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				EventosDoCalendario evento = new EventosDoCalendario(Long.parseLong(resultSet.getString("id")),
																	resultSet.getString("titulo"),
																	resultSet.getString("inicio"),
																	resultSet.getString("fim"));
				
				eventos.add(evento);
			}
			
			return eventos;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;	
	}
}
