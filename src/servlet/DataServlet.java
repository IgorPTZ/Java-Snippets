package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HorariosDao;

@WebServlet("/pages/dataServlet")
public class DataServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private HorariosDao horariosDao = new HorariosDao();
       
    public DataServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			int horasPorDia = 8;
			
			Double totalDeDias = 0.0;
			
			Date dataCalculada = null;
			
			String dataInicial = request.getParameter("dataInicial");
			
			int tempo = Integer.parseInt(request.getParameter("tempo"));
			
			if(tempo <= horasPorDia) {
				
				dataCalculada = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicial);
				
				totalDeDias = 1.0;
			}
			else {
				
				totalDeDias = (double) (tempo/ horasPorDia);
				
				if(totalDeDias < 1) {
					
					dataCalculada = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicial);
				}
				else {
					
					Date dataInformada = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicial);
					
					Calendar calendar = Calendar.getInstance();
					
					calendar.setTime(dataInformada);
					
					// Adiciona uma quantidade de dias a uma data
					calendar.add(Calendar.DATE, totalDeDias.intValue());
					
					dataCalculada = calendar.getTime();
				}
			}
			
			String dataFinal = new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada);
			
			horariosDao.inserir(dataFinal);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/datas.jsp");
			
			request.setAttribute("dataFinal", dataFinal);
			
			request.setAttribute("totalDeDias", totalDeDias);
			
			dispatcher.forward(request, response);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}
}
