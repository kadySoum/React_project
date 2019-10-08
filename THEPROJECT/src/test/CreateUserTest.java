package test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateUserTest {
	public static void main(String[] args) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		JSONObject obj1 = services.user.ServiceCreateUser.createUser("Hatake","kakashi","sharingan","roman","kakashi@gmail.com",21,"F");
		System.out.println(obj1.toString());
		JSONObject obj2 = services.user.ServiceCreateUser.createUser("Usumaki","naruto","kyubi","nouille","naruto@gmail.com",84,"M");
		System.out.println(obj2.toString());
		JSONObject obj3 = services.user.ServiceCreateUser.createUser("Uchiwa","Sasuke","chiant","1234","sasuke@gmail.com",56,"M");
		System.out.println(obj3.toString());
		JSONObject obj4 = services.user.ServiceCreateUser.createUser("Uchiwa","Itachi","toto","nouille","itachi@gmail.com",28,"M");
		System.out.println(obj4.toString());
		services.user.ServiceCreateUser.createUser("Nara","Shikamaru","toto","nouille","shikamaru@gmail.com",25,"M");
	    services.user.ServiceCreateUser.createUser("Freecs","Gon","nen","smile","gon@gmail.com",14,"M");
		services.user.ServiceCreateUser.createUser("Zoldyck","Kirua","kirua","kirua","kirua@gmail.com",14,"M");
		services.user.ServiceCreateUser.createUser("Morow","Hisoka","hisoka","hisoka","hisoka@gmail.com",28,"M");
		
		JSONObject obj5 =services.user.ServiceCreateUser.createUser("Usumaki","naru","toto","nouille","na@gmail.com",21,"M");
		System.out.println(obj5.toString());
		
		JSONObject ob =services.user.ServiceCreateUser.userID("saitama");
		System.out.println(ob.toString());
		
	}
}
