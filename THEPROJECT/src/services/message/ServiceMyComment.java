package services.message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class ServiceMyComment {
	/**
	 * pour trouver uniquement les messages de l'utilisateur 
	 * (page principale ET page de profil
	 * @param key
	 * @return
	 * @throws JSONException
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static JSONObject MyMessage(String key) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if (key== null){
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
		
	
		List<Document> res = new ArrayList<Document>();
		List<Document> res2 = new ArrayList<Document>();
		
		//tous les messages de l'utilisateur
		res =tools.mongoTools.trouver("comments",login);
		
		//tous les messages contenant le login de l'utilisateur
		List liste =tools.FriendTools.getFriendships(id_log);//liste de followers et following 
		List <JSONObject> listJ  =new ArrayList<JSONObject>();
		System.out.println("ooooooLISTE "+ liste.toString());
		for(int i=0; i<liste.size(); i++){
			JSONObject obj = new JSONObject();
			int o = Integer.parseInt(liste.get(i).toString());
			System.out.println("oooooo"+ o);
			res=(tools.mongoTools.trouver(login,login,o));
			listJ.add(obj);
		}
		
		JSONObject reponse = new JSONObject();
		reponse.put("liste", res);
		reponse.put("liste_follow",listJ );
		return  reponse;
		
	
	}
	public static JSONObject couleur() throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
	
		List<Document> res = new ArrayList<Document>();
		res =tools.mongoTools.color();
		System.out.println("res"+res);
		//tous les messages contenant le login de l'utilisateur
		List <JSONObject> listJ  =new ArrayList<JSONObject>();
		for(int i=0; i<res.size(); i++){
			JSONObject obj = new JSONObject();
			System.out.println(res.get(i));
			//listJ.add(res.get(i));
		}
		
		JSONObject reponse = new JSONObject();
		reponse.put("color",listJ );
		return  reponse;
		
	}	



}