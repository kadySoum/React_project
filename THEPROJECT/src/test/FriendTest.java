package test;

import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;


public class FriendTest {



	public static void main(String[] args) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{	
		
		JSONObject objAdd = services.friend.ServiceAddFriend.addFriend("jebdbplrxjnfneqnryweacjcgsjabcvy",2);//6 et 2
		System.out.println(objAdd.toString());
		
		JSONObject objAdd2 = services.friend.ServiceAddFriend.addFriend("jebdbplrxjnfneqnryweacjcgsjabcvy",7);
		System.out.println(objAdd2.toString());
		
		JSONObject objRemnew = services.friend.ServiceRemoveFriend.removeFriend("ozclsixncwmhshtasgaixrmfvbuqeiqd",10);
		System.out.println(objRemnew.toString());
		
		JSONObject objRem2 = services.friend.ServiceRemoveFriend.removeFriend("ozclsixncwmhshtasgaixrmfvbuqeiqd",8);
		System.out.println(objRem2.toString());
		
		
		
		/*JSONObject objAdd = services.friend.ServiceAddFriend.addFriend(3,2);
		System.out.println(objAdd.toString());
		
		JSONObject objAdd2 = services.friend.ServiceAddFriend.addFriend(6,3);
		System.out.println(objAdd2.toString());
		
		JSONObject objRem = services.friend.ServiceRemoveFriend.removeFriend(2,1);
		System.out.println(objRem.toString());
		
		JSONObject objAddnew = services.friend.ServiceAddFriend.addFriend(3,5);
		System.out.println(objAddnew.toString());
		JSONObject objRemnew = services.friend.ServiceRemoveFriend.removeFriend(7,6);
		System.out.println(objRemnew.toString());
		
		JSONObject objRem2 = services.friend.ServiceRemoveFriend.removeFriend(3,6);
		System.out.println(objRem2.toString());
		
		//liste test
		JSONObject objAddn = services.friend.ServiceAddFriend.addFriend(3,6);
		System.out.println(objAddn.toString());
		JSONObject objAd = services.friend.ServiceAddFriend.addFriend(3,1);
		System.out.println(objAd.toString());
		JSONObject obj = services.friend.ServiceAddFriend.addFriend(4,3);
		System.out.println(obj.toString());*/
		JSONObject o =  services.friend.ServiceListFriend.listFriend(7);
		System.out.println(o.toString());
		JSONObject o2 =  services.friend.ServiceListFriend.listPeople("ykqccyoqzqhvsfsxfzmnrbjntzahstoo");
		System.out.println(o2.toString());
		
	}
}