package services.user;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;


public class ServiceCreateUser {
	//Entree =>prenom & nom & login & password 
	//Sortie =>{}
	
	public static JSONObject createUser(String nom, String prenom, String login,String password, String mail,int  age,String sexe) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		if ((prenom==null) || (nom== null) || (password == null) || (login == null) || (mail ==null) || age==0 || (sexe==null)){
			return tools.ErrorJSON.serviceRefused("Au moins un des parametre est null", -1);
		}
		boolean is_mail = tools.UserTools.mailExists( mail);
		if(is_mail){
			return tools.ErrorJSON.serviceRefused("Cette adresse mail est déjà utilisées", 1002);
		}
		boolean is_user = tools.UserTools.userExists(login);
		if(is_user)
			return tools.ErrorJSON.serviceRefused("User existe deja !", 1001);
		try {
			tools.UserTools.insertUser(login, password, nom, prenom,mail,age, sexe);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//cle++;
		return tools.ErrorJSON.serviceAccepted("Ok creation user");  // on renvoie un ok 
	}

	public static JSONObject userID(String login) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if(login ==null) {
			return tools.ErrorJSON.serviceRefused("Mauvaise key", -1);
		}
		int id = tools.UserTools.getIdUser(login);
		String id_user =String.valueOf(id);
		return tools.ErrorJSON.buildJSON("id",id_user);
		
	}

}
