package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class ServletSearchMessage extends HttpServlet{
	
	public void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String key = request.getParameter("key");
		String query = request.getParameter("query");
		int id_friend = Integer.parseInt(request.getParameter("id_friend"));
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		try {
			try {
				out.print(services.message.ServiceSearchMessage.searchMessage(key,query,id_friend));//(log));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}