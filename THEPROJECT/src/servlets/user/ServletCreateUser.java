package servlets.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class ServletCreateUser extends HttpServlet{

	 public ServletCreateUser() {
	 }
	 
	 protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException{
		 
		 String nom = request.getParameter("nom");
		 String prenom = request.getParameter("prenom");
		 String log = request.getParameter("login");
		 String pwd = request.getParameter("password");
		 String mail = request.getParameter("mail");
		 String age = request.getParameter("age");
		 String sexe = request.getParameter("sexe");
		 
		 //on peut ajouter adresse mail 
		 int a= Integer.parseInt(age);
		 response.setContentType("application/json");
		 
		 PrintWriter out = response.getWriter();

			try {
				try {
					out.print(services.user.ServiceCreateUser.createUser(nom,prenom,log, pwd, mail,a,sexe).toString());
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
