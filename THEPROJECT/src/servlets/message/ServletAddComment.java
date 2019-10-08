package servlets.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class ServletAddComment extends HttpServlet{
	public ServletAddComment(){}
	
	public void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	/*	String log = request.getParameter("login");
		String commentaire = request.getParameter("comment");
		*/
		String key = request.getParameter("key");
		String commentaire = request.getParameter("comment");
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		try {
			try {
				out.print(services.message.ServicesAddComment.addComment(key, commentaire));
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
