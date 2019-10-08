package tools;

//import com.mysql.jdbc.Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import com.mysql.jdbc.Statement;
import java.util.Random;

import bd.DataBase;

public class UserTools {
	//select * from User where login='login' and password ='password'
	public static boolean checkPassword(String login,String password) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		//pour eviter les erreur avec tomcat et phpmyadmin
		//pour avoir acces ï¿½ phpmyadmin via tomcat
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		Connection co = bd.DataBase.getMySQLConnection();
		Statement st = co.createStatement();// Statement : permettent dï¿½envoyer une requï¿½te SQL de sï¿½lection ou de mise ï¿½ jour 
		String Query = "select * from user where login=\'"+login+"\' and password=\'"+password+"\';" ;
		ResultSet res = st.executeQuery(Query);//ResultSet tableau avec les valeurs de retour de la requete SQL
		boolean b = false;
		
		/*next() : dï¿½place le curseur sur la ligne suivante.
		Elle retourne true si le curseur est positionnï¿½ sur une ligne, 
		false si le curseur a dï¿½passï¿½ la fin du tableau. 
		La valeur du retour de cette mï¿½thode doit obligatoirement ï¿½tre testï¿½e avant la lecture de la ligne courante.*/
		
		if (res.next()){
			b=true;
		}
		co.close();
		st.close();
		res.close();
		return b;
	}
				
	//select *  from User where login ='login';
	public static boolean userExists(String login) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		Connection co = bd.DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select * from user where login =\'"+login+"\';";
		ResultSet res = st.executeQuery(Query);
		boolean b = false; 
		if (res.next()){
			b = true;
		}
		co.close();
		st.close();
		res.close();
		return b;
	}
	/**
	 * verifier si le mail est deja utilise
	 * @param mail
	 * @return
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static boolean mailExists(String mail) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		Connection co = bd.DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select * from user where mail =\'"+mail+"\';";
		ResultSet res = st.executeQuery(Query);
		boolean b = false; 
		if (res.next()){
			b = true;
		}
		co.close();
		st.close();
		res.close();
		return b;
	}
			
		public static boolean userCoKey(String key) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		Connection co = bd.DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select * from session where skey =\'"+key+"\';";
		ResultSet res = st.executeQuery(Query);
		boolean b = false; 
		if (res.next()){
			b = true;
		}
		co.close();
		st.close();
		res.close();
		return b;
		}
	
	/**
	 * pour le service Friend
	 * @param id_user
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	//select * from USer where id='id_user';
	public static boolean userExistsId(int id_user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		Connection co =bd.DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select * from user where id=\'"+id_user+"\';";
		ResultSet res = st.executeQuery(Query);
		boolean b = false;
		if (res.next()){
			b = true;
		}
		co.close();
		st.close();
		res.close();
		return b;
	}
	
	public static ArrayList  getPeople() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		ArrayList people = new ArrayList();
		Connection co = bd.DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select login from user ";  
		ResultSet res = st.executeQuery(Query);
		//liste des gens que user suit = abonnements
		while(res.next()){
			//on recupere element courant
			String a = res.getString("login");
			
			if(!people.contains(a)) {
				people.add(a);}
			//amis.add(b);
		}
		st.close();
		return people;
		
	}

	public static int getIdUser(String login) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		int id;
		 
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select id from user where login='"+login+"\'";  
		ResultSet res = st.executeQuery(Query);
		res.next();
		id =  res.getInt("id");
		
		res.close();
		co.close();
		st.close();
		
		return id;
	}
	/**
	 * retourne la cle d'un utiisateur connecter grace à son login
	 * @param login
	 * @return
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static String getKey(String login) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	int id = getIdUser(login);
		String cle;
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select skey from session,user where login=\'"+login+"\' and dateC=dateD and id_user="+id+";";  
		ResultSet res = st.executeQuery(Query);
		res.next();
		cle =  res.getString("skey");
		
		res.close();
		co.close();
		st.close();
		
		return cle;
	}
	
	/**
	 * retourne le login de l'utilisateur avec l'id
	 * @param id_user
	 * @return
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static String getLogin(int id_user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select login from user where id='"+id_user+"\'";  
		ResultSet res = st.executeQuery(Query);
		res.next();
		String log =  res.getString("login");
		
		res.close();
		co.close();
		st.close();
		
		return log;
	}
	
	public static String getLName(int id_user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select nom from user where id='"+id_user+"\'";  
		ResultSet res = st.executeQuery(Query);
		res.next();
		String nom =  res.getString("nom");
		
		res.close();
		co.close();
		st.close();
		
		return nom;
	}
	
	public static String getFName(int id_user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select prenom from user where id='"+id_user+"\'";  
		ResultSet res = st.executeQuery(Query);
		res.next();
		String nom =  res.getString("prenom");
		
		res.close();
		co.close();
		st.close();
		
		return nom;
	}
	
	public static String getMail(int id_user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select mail from user where id='"+id_user+"\'";  
		ResultSet res = st.executeQuery(Query);
		res.next();
		String nom =  res.getString("mail");
		
		res.close();
		co.close();
		st.close();
		
		return nom;
	}
	
	
	/**
	 * retourne une cle/key pour login 
	 * @param login
	 * @return
	 */
	public static String insertConnexion(int id_user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp dateC = new java.sql.Timestamp(utilDate.getTime());
		
		java.util.Date utilDate1 = new java.util.Date();
		java.sql.Timestamp dateD = new java.sql.Timestamp(utilDate1.getTime());
		
		int root=0;
		if(id_user==1 || id_user==2)
			root=1;
		String key = generateKey();
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		
		String Query = "insert into session (skey, id_user, dateC, root, dateD) values (\'"+key+"\',\'"+id_user+"\',\'"+dateC+"\',\'"+root+"\',\'"+dateD+"\\'')";
		int res = st.executeUpdate(Query);
		
		co.close();
		st.close();
		
		//return "OK: insertion dans la table de connexion: ok\n"
		//return id_user+","+getLogin(id_user)+","+key;
		return key;
	}
	/**
	 * genere un cle aleatoire pour la connexion 
	 * @return
	 */
	 public static String generateKey(){
			Random r = new Random();
			
			String key ="";
			for (int i =0; i<32; i++){
				key+=(char)(r.nextInt(26) + 'a');
			}
			return key;
	}

	
	
	
	/**
	 * retourne true si l'utilisateur est connectï¿½, si son id est dans la table des connections
	 * @param key
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static boolean userConnect(int user_key) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select id_user from session where id_user=\'"+user_key+"\'";
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
	public static boolean userIsConnect(int user_key) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		//String Query = "select DATEDIFF(second, dateC, dateD) as sec from session where id_user=\'"+user_key+"\'";
		String Query = "select count(*) from session where dateC=dateD and id_user=\'"+user_key+"\'";
		
		ResultSet res = st.executeQuery(Query);
		res.next();
		int cle =  res.getInt("count(*)");//DATEDIFF(SECOND, dateC, dateD)");
		//System.out.println("CLEEEEEE =>"+cle);
		if(cle==1){
			return true;//il est connecte
		}
		
		res.close();
		co.close();
		st.close();
		
		return false;
	}
	
	
	
	public static int id_USER(String key) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "select id_user from session where skey=\'"+key+"\'";
		ResultSet res = st.executeQuery(Query);
		res.next();
		int cle =  res.getInt("id_user");
		
		res.close();
		co.close();
		st.close();
		
		return cle;
	}
	
	public static int insertUser(String login,String password,String nom,String prenom,String mail, int age, String sexe) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		Connection co = bd.DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "insert into user (nom, prenom, login, password,mail,age,sexe) values (\'"+nom+"\',\'"+prenom+"\',\'"+login+"\',"
				+ "\'"+password+"\',\'"+mail+"\',\'"+age+"\',\'"+sexe+"\');" ;
		int res = st.executeUpdate(Query);
		
		co.close();
		st.close();
		return res;
	}
	
	public static String removeConnexion(int key_user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		String Query = "delete  from session where id_user=\'"+key_user+"\'" ; 
		int res = st.executeUpdate(Query);
		
		co.close();
		st.close();
		return "Deconnexion faite dans la base";
	}
	
	public static String actif_ou_NON(String key_user) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	
		Connection co = DataBase.getMySQLConnection();
		Statement st = co.createStatement();
		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp dateD = new java.sql.Timestamp(utilDate.getTime());
		
		String Query = "UPDATE session set dateD=\'"+dateD+"\' where skey=\'"+key_user+"\'" ; 
		int res = st.executeUpdate(Query);
		
		co.close();
		st.close();
		return "Deconnexion faite dans la base";
	}
	

}
