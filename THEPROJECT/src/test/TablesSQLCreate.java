
package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONException;

import bd.DataBase;

public class TablesSQLCreate {
	public static void UsersTable() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	Connection connection = bd.DataBase.getMySQLConnection();
		String requete = String.format("%s%s%s%s%s%s%s%s%s%s",
        		"CREATE TABLE user(","id Integer PRIMARY KEY AUTO_INCREMENT,","nom Varchar(64),","prenom Varchar(64),","login Varchar(32) UNIQUE,","password Blob NOT NULL,","mail Varchar(64) UNIQUE",",age INTEGER(64)",",sexe Varchar (255)",");");
        PreparedStatement st = connection.prepareStatement(requete);
        st.executeUpdate();
        st.close();
        connection.close();
	}
	
	public static void SessionsTable() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection connection = DataBase.getMySQLConnection();
		String requete = String.format("%s%s%s%s%s%s%s%s",
        		"CREATE TABLE session(","id Integer UNIQUE AUTO_INCREMENT,","skey Varchar(32) UNIQUE,","id_user Integer ,","dateC Timestamp,","dateD Timestamp NULL,","root boolean",");");
		PreparedStatement st = connection.prepareStatement(requete);
        st.executeUpdate();
        st.close();
        connection.close();
	}
	
	public static void FriendsTable() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection connection = DataBase.getMySQLConnection();
		String requete =String.format("%s%s%s%s%s",
        		"CREATE TABLE friends(",
					"id Integer UNIQUE AUTO_INCREMENT,",
        			"id_user Integer,",
        			"id_friend Integer",
        			");");
		PreparedStatement st = connection.prepareStatement(requete);
        st.executeUpdate();
        st.close();
        connection.close();
	}
	public static void main(String[] args) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
		UsersTable();
	    SessionsTable();
	    FriendsTable();
	}
}
	
	/*public static void UsersTable() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	Connection connection = bd.DataBase.getMySQLConnection();
		String requete = String.format("%s%s%s%s%s%s%s%s%s%s",
        		"CREATE TABLE user(",
        		"id int(11) PRIMARY KEY AUTO_INCREMENT,",//ou Integer
        		"login Varchar(32) UNIQUE NOT NULL,",
        		"password blob NOT NULL,",
        		"prenom Varchar(255) NOT NULL,",
        		"nom Varchar(255) NOT NULL,",
        		"mail Varchar(225) UNIQUE",
        		"age int(11) NOT NULL",
        		"sexe varchar(32) NOT NULL",
        		");");
        PreparedStatement st = connection.prepareStatement(requete);
        st.executeUpdate();
        st.close();
        connection.close();
	}*/
	
	

