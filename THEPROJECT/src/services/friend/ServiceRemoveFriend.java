package services.friend;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceRemoveFriend {
	
	public static JSONObject removeFriend(String key ,int id_friend) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(key==null ||id_friend ==0){
			tools.ErrorJSON.serviceRefused("un des parametres est invalide(=0) : servlet remove friend", -1);
		}
		
		//verifie si il existe dans la table Session
				boolean is_user = tools.UserTools.userCoKey(key);
				boolean is_friend = tools.UserTools.userExistsId(id_friend);
		
		if(!is_user || !is_friend){
			return tools.ErrorJSON.serviceRefused("un des utilisateurs n'existe pas ", 1000);
		}
		
		//si il n'est pas connecte avec date 
				int id_user = tools.UserTools.id_USER(key);//recupere l'id_user dans session
				boolean connect = tools.UserTools.userIsConnect(id_user);
						
				if(!connect){
					return tools.ErrorJSON.serviceRefused("l'utilisateur n'est pas connecte ", 1000); 
				}
		
		//verifie si il est connect�
				boolean is_connect = tools.UserTools.userConnect(id_user);
				if (!is_connect){
					return tools.ErrorJSON.serviceRefused("Vous n'etes pas connecte", 1000);
				}
		
				
		
		boolean is_friendship = tools.FriendTools.friendshipExists(id_user, id_friend);
		
		if(!is_friendship){
			return tools.ErrorJSON.serviceRefused("ils ne sont pas amis. Aucune suppression necessaire", 1000);
		}
		tools.FriendTools.removeFriendship(id_user, id_friend);
		return tools.ErrorJSON.serviceAccepted("ami supprim�");
	}
}
	
	/*public static JSONObject removeFriend(int id_user, int id_friend) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(id_user==0 ||id_friend ==0){
			tools.ErrorJSON.serviceRefused("un des parametres est invalide(=0) : servlet remove friend", -1);
		}
		boolean is_user = tools.UserTools.userExistsId(id_user);
		boolean is_friend = tools.UserTools.userExistsId(id_friend);
		
		if(!is_user || !is_friend){
			return tools.ErrorJSON.serviceRefused("un des utilisateurs n'existe pas ", 1000);
		}
		//verifie si il est connect�
				boolean is_connect = tools.UserTools.userConnect(id_user);
				if (!is_connect){
					return tools.ErrorJSON.serviceRefused("Vous n'etes pas connecte", 1000);
				}
		
		boolean is_friendship = tools.FriendTools.friendshipExists(id_user, id_friend);
		
		if(!is_friendship){
			return tools.ErrorJSON.serviceRefused("ils ne sont pas amis. Aucune suppression necessaire", 1000);
		}
		tools.FriendTools.removeFriendship(id_user, id_friend);
		return tools.ErrorJSON.serviceAccepted("ami supprim�");
	}*/

