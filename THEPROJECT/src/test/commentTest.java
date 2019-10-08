package test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class commentTest {
	public static void main(String[] args) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		/*JSONObject obj1 = services.message.ServicesAddComment.addComment("hisoka","humm humm");
		System.out.println(obj1.toString());
		JSONObject objLog = services.user.ServiceLogin.login("hisoka","hisoka");
		System.out.println(objLog.toString());
		JSONObject obj = services.message.ServicesAddComment.addComment("zaukrntjjudlccmxfbxdmxfcctzkvhem","bonjour je m'appelle kirua !");
		System.out.println(obj.toString());
		*/
		/*JSONObject obj2 = services.message.ServiceMyComment.MyMessage("mlrwvuvnyhtcjfupvhsrtkxvknzrnper");
		System.out.println(obj2.toString());*/
		JSONObject obj2 = services.message.ServiceMyComment.couleur();
		System.out.println(obj2.toString());
	}
}
