package bd;

import java.sql.DriverManager;

import java.sql.Statement;

import java.sql.Connection;


public class TestJDBC {
	public static void main (String [] args) throws Exception {
		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/ly_soumahoro";
			Connection co= DriverManager.getConnection(url,"root","root");
			//String q = "Select * from user ";
			String nom = "toto";
			String prenom = "tata";
			String password = "111";
			String login = "ko0";
			String mail = "aa@gmail.com";
			String age = "21";
			String sexe = "F";
			String q = "insert into user (login, password, nom, prenom,mail,age,sexe) values (\'"+login+"\',\'"+password+"\',\'"+prenom+"\',"
					+ "\'"+nom+"\',\'"+mail+"\',\'"+age+"\',\'"+sexe+"\');" ;
			Statement st = co.createStatement();
			int rs = st.executeUpdate(q);
		

			co.close();
			st.close();
			System.out.println("Create user: "+rs);
			
			return;
	}}
			
			/*while(rs.next()) {
				System.out.println("id: "+rs.getInt(1));
				System.out.println("login: "+rs.getString("login"));
				//String password = rs.getString("password");
			//}
			//rs.close();
			st.close();
			co.close(); */