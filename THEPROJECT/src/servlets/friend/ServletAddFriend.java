package servlets.friend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

public class ServletAddFriend extends HttpServlet{
	
	public ServletAddFriend(){
		
	}
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException{
	
		String user = request.getParameter("key");
		String friend = request.getParameter("id_friend");
	
		//int value_user = Integer.parseInt(user);
		int value_friend = Integer.parseInt(friend);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		

		try {
			try {
				out.print(services.friend.ServiceAddFriend.addFriend(user,value_friend));
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
