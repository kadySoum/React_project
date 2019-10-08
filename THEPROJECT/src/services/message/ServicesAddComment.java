package services.message;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class ServicesAddComment {
	public static JSONObject addComment(String key,String texte) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if (key==null|| texte == null){
			return tools.ErrorJSON.serviceRefused("un des parametres est null", -1);
		}
		//verifier que la key est correcte(existe...)
		boolean b = tools.UserTools.userCoKey(key);
		if (!b){
			return tools.ErrorJSON.serviceRefused("cle inexistante", -1);
			 
		}
		//fonction qui recupere le login
				int id_log = tools.UserTools.id_USER(key);
				String login = tools.UserTools.getLogin(id_log);
		
		
		//verifie si il existe
		boolean is_user = tools.UserTools.userExists(login);
		if(!is_user){
			return tools.ErrorJSON.serviceRefused("l'utilisateur n'existe pas",1000 );
		}
		//verifie si il est connecte =>present dans la session 
		int key_user = tools.UserTools.getIdUser(login);
		boolean is_connect = tools.UserTools.userConnect(key_user);
		if (!is_connect){
			return tools.ErrorJSON.serviceRefused("Vous n'etes pas connecte", 1000);
		}
		//si il n'est pas connecte avec date
		int id_user = tools.UserTools.id_USER(key);//recupere l'id_user dans session
		boolean connect = tools.UserTools.userIsConnect(id_user);
		
		if(!connect){
			return tools.ErrorJSON.serviceRefused("l'utilisateur n'est pas connecte ", 1000); 
		}

		tools.mongoTools.addComment("comments", login, texte);
		return tools.ErrorJSON.serviceAccepted("Votre commentaire vient d'�tre ajoute");
	}
}
