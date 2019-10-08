package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FriendTools {
	public static boolean friendshipExists(int user, int friend) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
			Connection co = bd.DataBase.getMySQLConnection();
			Statement st = co.createStatement();
			String Query = "select * from friends where id_user =\'"+user+"\' AND id_friend = \'"+friend+"\'";
			ResultSet res = st.executeQuery(Query);
			boolean b = false;
			if (res.next()){	
				b=true;
			}
			co.close();
			st.close();
			res.close();
			return b;
	}
	
	public static String  insertFriendship(int id_user, int id_friend) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		System.out.print("ok insertFriendship tool");
		
		Connection co = bd.DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "insert into friends (id_user, id_friend) values (\'"+id_user+"\',\'"+id_friend+"\')";
		int res = st.executeUpdate(Query);
			
		co.close();
		st.close();
		 
		 return "ok";
	}
	public static String removeFriendship(int id_user, int id_friend) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		Connection co = bd.DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "delete from friends where id_user =\'"+id_user+"\' AND id_friend = \'"+id_friend+"\'";
		int res = st.executeUpdate(Query);
			
		co.close();
		st.close();
		System.out.print("ok removeFriendship tool"); 
		return "ok";
		
	}
	/**
	 * afficher la liste d'amis d'un utilisateur
	 * @param id
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList getFriendships(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		 
		ArrayList amis = new ArrayList();
		
		 
		Connection co = bd.DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select * from friends where id_user =\'"+id+"\'";  
		ResultSet res = st.executeQuery(Query);
		//liste des gens que user suit = abonnements
		while(res.next()){
			//on recupere element courant
			int a = res.getInt("id_friend");
			
			if(!amis.contains(a)) {
				amis.add(a);}
			//amis.add(b);
		}
		st.close();
		
		//liste des gens qui suivent user= abonn�s
		Statement st2 = co.createStatement();
		
		String Query2 = "select * from friends where id_friend =\'"+id+"\'";  
		ResultSet res2 = st2.executeQuery(Query2);
		while(res2.next()){
			int b = res2.getInt("id_user");
			if(!amis.contains(b)) {
				amis.add(b);}
		}
		
		return amis;
		 
	 }
	
	public static ArrayList getFollowing(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ArrayList amis = new ArrayList();
		Connection co = bd.DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select * from friends where id_user =\'"+id+"\'";  
		ResultSet res = st.executeQuery(Query);
		//liste des gens que user suit = abonnements
		while(res.next()){
			//on recupere element courant
			int a = res.getInt("id_friend");
			
			if(!amis.contains(a)) {
				amis.add(a);}
			//amis.add(b);
		}
		st.close();
		return amis;
	}
	
	public static ArrayList getFollowers(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ArrayList amis = new ArrayList();
		Connection co = bd.DataBase.getMySQLConnection();
		//liste des gens qui suivent user= abonn�s
		Statement st2 = co.createStatement();
				
		String Query2 = "select * from friends where id_friend =\'"+id+"\'";  
		ResultSet res2 = st2.executeQuery(Query2);
		while(res2.next()){
			int b = res2.getInt("id_user");
			if(!amis.contains(b)) {
				amis.add(b);}
		}
		return amis;		 
	}
}
