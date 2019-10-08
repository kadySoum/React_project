package test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class LogoutTest {
	
	public static void main(String[] args) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			
			/*JSONObject lo = services.user.ServiceLogout.logout("kyubi");
			System.out.println(lo.toString());
			JSONObject o = services.user.ServiceLogout.logout("nen");//, false);
			System.out.println(o.toString());*/
		JSONObject lo = services.user.ServiceLogout.logout("oitbxayhlvjxeiwubewoarzvsrpvyimc");
		System.out.println(lo.toString());
		JSONObject o = services.user.ServiceLogout.logout("nen");//, false);
		System.out.println(o.toString());
				
		}

}
