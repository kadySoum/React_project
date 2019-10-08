package services.user;



import org.json.JSONObject;
import org.json.JSONException;

import java.sql.SQLException;

@SuppressWarnings("unused")
public class ServiceLogin {
	
	//Entree =>login &password 
	//Sortie =>{id, login, key}
		
	public static JSONObject login(String login, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		if((login==null) || (password==null)){
			return tools.ErrorJSON.serviceRefused("Mauvais argument", -1);
		}
		boolean is_user = tools.UserTools.userExists(login);
		if(!is_user){
			return tools.ErrorJSON.serviceRefused("Cet utilisateur n'existe pas", 1002);
		}
		boolean pwd = tools.UserTools.checkPassword(login, password);//mot de passe ok 
		if(!pwd){
			return tools.ErrorJSON.serviceRefused("Mauvais mot de passe. Essayez encore ", 1000);
		}
		int id_user = tools.UserTools.getIdUser(login);
		boolean connect = tools.UserTools.userIsConnect(id_user);
		
		if(connect){
			return tools.ErrorJSON.serviceRefused("l'utilisateur est deja�connecte ", 1001); 
		}
		/*
		boolean connect = tools.UserTools.userConnect(cle_user);//connection ok
		if(connect){
			return tools.ErrorJSON.serviceRefused("l'utilisateur est deja�connecte ", 1000); 
		}*/
		String key = tools.UserTools.insertConnexion(id_user);
			
		return  tools.ErrorJSON.buildJSON("cle", key);
					
	} //penser à ajouter un try catch pour SQLException
	
	
	public static JSONObject user(String login) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if(login ==null) {
			return tools.ErrorJSON.serviceRefused("Mauvaise key", -1);
		}
		String cle = tools.UserTools.getKey(login);
		//String s =String.valueOf(id);
		return tools.ErrorJSON.buildJSON("cle",cle);
		
	}
	
	
	
}
