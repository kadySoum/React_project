package servlets.friend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class ServletListFriend extends HttpServlet{

	public ServletListFriend(){}
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id_user = request.getParameter("id_user");
		int value_user = Integer.parseInt(id_user);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		
		try {
			out.println(services.friend.ServiceListFriend.listFriend(value_user));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
