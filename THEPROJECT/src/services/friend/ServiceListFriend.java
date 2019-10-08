package services.friend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.util.JSON;

public class ServiceListFriend {
	public static JSONObject listFriend(int id_user) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException{
		if(id_user==0){
			return tools.ErrorJSON.serviceRefused("parametre null", -1);
		}
		boolean is_user= tools.UserTools.userExistsId(id_user);
		if (!is_user){
			return tools.ErrorJSON.serviceRefused("l'utilisateur n'existe pas", -1000);
		}
		List liste =tools.FriendTools.getFriendships(id_user);
		List following = tools.FriendTools.getFollowing(id_user);
		List followers = tools.FriendTools.getFollowers(id_user);
		
		JSONArray a = new JSONArray(liste);
		JSONArray fg = new JSONArray(following);
		JSONArray fw = new JSONArray(followers);
		
		//System.out.println(liste.size());
		List <JSONObject> listJ  =new ArrayList<JSONObject>();
		for(int i=0; i<a.length(); i++){
			JSONObject obj = new JSONObject();
			obj.put("id_test", a.getInt(i));
			obj.put("login", tools.UserTools.getLogin(a.getInt(i)));
			obj.put("nom", tools.UserTools.getLName(a.getInt(i)));
			obj.put("prenom", tools.UserTools.getFName(a.getInt(i)));
			obj.put("mail", tools.UserTools.getMail(a.getInt(i)));
			listJ.add(obj);
		}
		JSONArray res = new JSONArray(listJ);
		
		List <JSONObject> listFg  =new ArrayList<JSONObject>();
		for(int i=0; i<fg.length(); i++){
			JSONObject obj = new JSONObject();
			obj.put("id_test", fg.getInt(i));
			obj.put("login", tools.UserTools.getLogin(fg.getInt(i)));
			obj.put("nom", tools.UserTools.getLName(fg.getInt(i)));
			obj.put("prenom", tools.UserTools.getFName(fg.getInt(i)));
			obj.put("mail", tools.UserTools.getMail(fg.getInt(i)));
			listFg.add(obj);
		}
		JSONArray resFg = new JSONArray(listFg);
		
		List <JSONObject> listFw  =new ArrayList<JSONObject>();
		for(int i=0; i<fw.length(); i++){
			JSONObject obj = new JSONObject();
			obj.put("id_test", fw.getInt(i));
			obj.put("login", tools.UserTools.getLogin(fw.getInt(i)));
			obj.put("nom", tools.UserTools.getLName(fw.getInt(i)));
			obj.put("prenom", tools.UserTools.getFName(fw.getInt(i)));
			obj.put("mail", tools.UserTools.getMail(fw.getInt(i)));
			listFw.add(obj);
		}
		JSONArray resFw = new JSONArray(listFw);
		
		JSONObject reponse = new JSONObject();
		reponse.put("liste", res);
		reponse.put("following", resFg);
		reponse.put("followers", resFw);
		return  reponse;
		//return tools.ErrorJSON.serviceAccepted(a.toString()+"___________"+fg.toString()+"___________"+fw.toString());
	}
	
	public static JSONObject listPeople(String cle) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException{
		if(cle==null){
			return tools.ErrorJSON.serviceRefused("parametre null", -1);
		}
		int id_user =tools.UserTools.id_USER(cle) ;
		List following = tools.FriendTools.getFollowing(id_user);
		
		JSONArray fg = new JSONArray(following);
		
		List people = tools.UserTools.getPeople();
		for(int i=0; i<fg.length(); i++) {
			String login = tools.UserTools.getLogin(fg.getInt(i));
			System.out.println("login => "+ login);
			people.remove(login);
			
		}
		JSONArray res = new JSONArray(people);
		JSONObject reponse = new JSONObject();
		reponse.put("people",res);
		return  reponse;
		}

}
