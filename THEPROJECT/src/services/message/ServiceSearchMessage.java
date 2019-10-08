package services.message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceSearchMessage {
	public static JSONObject searchMessage(String key, String query, int id_friend) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if (key== null | query ==null||id_friend ==0){
			return tools.ErrorJSON.serviceRefused("un des parametres est null", -1);
		}
		//fonction qui recupere le login
		int id_log = tools.UserTools.id_USER(key);
		String login = tools.UserTools.getLogin(id_log);
		
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
		
		//check liste amis => si il sont amis
		boolean f =tools.FriendTools.friendshipExists(id_log, id_friend); 
		if(!f) {
			return tools.ErrorJSON.serviceRefused("Vous ne pouvez voir les messages de cet utilisateur. Ajoutez le!", 1000);
		}
		List<Document> res = new ArrayList<Document>();
		
		//res =tools.mongoTools.trouver("comments",login);
		res =tools.mongoTools.trouver(login,query,id_friend);
		return tools.ErrorJSON.serviceAccepted("Resultat de la recherche :"+ res);
		
	}}
	/*
	public static JSONObject searchMessage(String login) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if (login== null){
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
		List<Document> res = new ArrayList<Document>();
		res =tools.mongoTools.trouver("comments",login);
		return tools.ErrorJSON.serviceAccepted("Resultat de la recherche :"+ res);
	}*/

