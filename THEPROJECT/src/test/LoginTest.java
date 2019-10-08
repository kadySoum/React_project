package test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginTest {

	public static void main(String[] args) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		/*JSONObject objLog = services.user.ServiceLogin.login("Usertest","table");
		System.out.println(objLog.toString());*/
		/*JSONObject log = services.user.ServiceLogin.login("kirua","kirua");
		System.out.println(log.toString());
		JSONObject lo = services.user.ServiceLogin.login("kyubi","nouille");
		System.out.println(lo.toString());

		JSONObject o = services.user.ServiceLogin.login("chiant","1234");
		System.out.println(o.toString());
		*/
		JSONObject o = services.user.ServiceLogin.user("saitama");
		System.out.println(o.toString());
		
	}
}