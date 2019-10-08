package services.user;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceLogout {
	
	public static JSONObject logout(String key) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if (key== null){
			return tools.ErrorJSON.serviceRefused("un des parametres est null", -1);
		}
		
		//verifie si il existe dans la table Session
		boolean is_user = tools.UserTools.userCoKey(key);
		if(!is_user){
			return tools.ErrorJSON.serviceRefused("l'utilisateur n'existe pas",1000 );
		}

		//si il n'est pas connecte
		int id_user = tools.UserTools.id_USER(key);//recupere l'id_user dans session
		boolean connect = tools.UserTools.userIsConnect(id_user);
		
		if(!connect){
			return tools.ErrorJSON.serviceRefused("l'utilisateur n'est pas connecte ", 1000); 
		}

		//supprime la connexion dans la table de connexion (SQL)
		tools.UserTools.actif_ou_NON(key);
		
		return tools.ErrorJSON.serviceAccepted("OK pour la deconnexion");
		
	}
	
	
	
	/*public static JSONObject logout(String login) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if ((login== null)){
			return tools.ErrorJSON.serviceRefused("un des parametres est null", -1);
		}
		//verifie si il existe
		boolean is_user = tools.UserTools.userExists(login);
		if(!is_user){
			return tools.ErrorJSON.serviceRefused("l'utilisateur n'existe pas",1000 );
		}
		//verifie si il est connect�
		int key_user = tools.UserTools.getIdUser(login);
		boolean is_connect = tools.UserTools.userConnect(key_user);
		if (!is_connect){
			return tools.ErrorJSON.serviceRefused("Vous n'etes pas connecte", 1000);
		}
		//supprime la connexion dans la table de connexion (SQL)
		tools.UserTools.removeConnexion(key_user);
		
		return tools.ErrorJSON.serviceAccepted("OK pour la deconnexion");
		
	}*/
	
}
