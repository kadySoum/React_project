package services.friend;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceAddFriend {

	public static JSONObject addFriend(String key, int id_friend) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if (key == null || id_friend==0 ){
			return tools.ErrorJSON.serviceRefused("un des parametres est nul", -1);
		}
		
		//si il n'est pas connecte => present dans la table session
		boolean is_connect = tools.UserTools.userCoKey(key);
		if(!is_connect){
			return tools.ErrorJSON.serviceRefused("Vous n'etes pas connecte", 1000 );
		}
		
		//si il n'est pas connecte avec heure
		int id_user = tools.UserTools.id_USER(key);//recupere l'id_user dans session
		boolean connect = tools.UserTools.userIsConnect(id_user);
				
		if(!connect){
			return tools.ErrorJSON.serviceRefused("l'utilisateur n'est pas connecte ", 1000); 
		}
		
		boolean is_user_friend = tools.UserTools.userExistsId(id_friend);
		
		if (!is_user_friend){
			return tools.ErrorJSON.serviceRefused("un des utilisateur user ou friend est inexistant", 1000);
		}
		
		
		boolean is_friendship = tools.FriendTools.friendshipExists(id_user,id_friend);
		if(is_friendship){
			return tools.ErrorJSON.serviceRefused("ils sont deja amis! :)", 1000);
		}
		
		tools.FriendTools.insertFriendship(id_user,id_friend);
		return tools.ErrorJSON.serviceAccepted("nouvelle amitie !!");
	}
}
		
	/*public static JSONObject addFriend(int id_user, int id_friend) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if (id_user == 0 || id_friend==0 ){
			return tools.ErrorJSON.serviceRefused("un des parametres est nul", -1);
		}
		boolean is_user= tools.UserTools.userExistsId(id_user);
		boolean is_user_friend = tools.UserTools.userExistsId(id_user);
		
		if (!is_user || !is_user_friend){
			return tools.ErrorJSON.serviceRefused("un des utilisateur user ou friend est inexistant", 1000);
		}
		//verifie si il est connect�
				boolean is_connect = tools.UserTools.userConnect(id_user);
				if (!is_connect){
					return tools.ErrorJSON.serviceRefused("Vous n'etes pas connecte", 1000);
				}
		
		boolean is_friendship = tools.FriendTools.friendshipExists(id_user,id_friend);
		if(is_friendship){
			return tools.ErrorJSON.serviceRefused("ils sont deja amis! :)", 1000);
		}
		
		tools.FriendTools.insertFriendship(id_user,id_friend);
		return tools.ErrorJSON.serviceAccepted("nouvelle amitie !!");
	}*/
//}
